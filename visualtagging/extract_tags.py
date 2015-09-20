import cv2
import os
import json
import pprint
import numpy as np
import pickle
from skvideo.io import VideoWriter
from clarifai.client import ClarifaiApi


class Video_Tag_Extract():
    def __init__(self, video_name):
        self.working_root   = '..'
        self.videos_root    = '../training_videos'
        self.images_root    = '../training_images'
        self.json_root      = '../jsons'
        self.video_name     = video_name
        self.modulus        = 20
        self.api            = ClarifaiApi()


    def extract_images_from_video(self, video_start, video_end, job_id):
        """
            input 
                self.video_name 
            output 
                json file
        """
        # set up
        video_range = range(video_start, video_end)

        cap = cv2.VideoCapture(os.path.join(self.videos_root, self.video_name))
        num_frames = cap.get(cv2.cv.CV_CAP_PROP_FRAME_COUNT)
        fps = cap.get(cv2.cv.CV_CAP_PROP_FPS)
        self.modulus = int(1.5*fps)
        print self.modulus
        width = cap.get(cv2.cv.CV_CAP_PROP_FRAME_WIDTH)
        height = cap.get(cv2.cv.CV_CAP_PROP_FRAME_HEIGHT)
        print width
        print height
        # assert False 

        cur_frame = 0
        while cur_frame < num_frames:
            print cur_frame
            has_frame, frame = cap.read()  # frame is (height, width, channels)
            if cur_frame in video_range:
                cv2.imshow('frame', frame)

                # write frame
                if (cur_frame - video_start) % self.modulus == 0:
                    img_filename = os.path.join(self.images_root, self.video_name[:self.video_name.find('.mp4')] + '_' + str(cur_frame) + '_job_id=' + str(job_id) + '.jpg')
                    cv2.imwrite(img_filename, frame)
                    print 'Written'

                k = cv2.waitKey(30) & 0xff
                if k == 27:
                    break

            cur_frame += 1

        cv2.destroyAllWindows()
        cap.release()


    def analyze_images(self, job_id):
        # analyze images in batch
        image_list = [x for x in os.listdir(self.images_root) if 'job_id=' + str(job_id) + '.jpg' in x][:self.api.get_info()['max_batch_size']]
        result = self.api.tag_images([open(os.path.join(self.images_root, x)) for x in image_list])['results']
        info = {}
        for idx in range(len(result)):
            info[str(idx)] = result[idx]['result']['tag']

        with open('data.json', 'w') as fp:
            json.dump(info, fp)


    def remove_images(self, video_start, video_end, job_id):
        image_list = [x for x in os.listdir(self.images_root) if 'job_id=' + str(job_id) + '.jpg' in x]#[:self.api.get_info()['max_batch_size']]
        for img_name in image_list:
            os.system('rm ' + os.path.join(self.images_root, img_name))



def write_video():
    # Set up 
    working_root = '../'
    videos_root = os.path.join(working_root, 'training_videos')
    video_start = 0
    video_end = 2000
    video_range = range(video_start, video_end)
    ext = '.mp4'
    input_video_name = 'ad' + ext
    output_video_name = input_video_name[:input_video_name.find(ext)] + '_' + str(video_start) + '-' + str(video_end) + '.mp4'
    # print output_video_name
    # assert False

    # set up reading video
    cap = cv2.VideoCapture(os.path.join(videos_root, input_video_name))
    num_frames = cap.get(cv2.cv.CV_CAP_PROP_FRAME_COUNT)
    fps = cap.get(cv2.cv.CV_CAP_PROP_FPS)
    width, height = 640, 360
    cur_frame = 0

    # set up writing video
    writer = cv2.VideoWriter(os.path.join(videos_root, output_video_name),-1,1,(width,height))
    # writer = VideoWriter(output_video_name, frameSize=(640, 360))  # can add fps=1 for 1 frame per second
    # writer.open()

    # main loop
    while cur_frame < num_frames:
        print cur_frame
        has_frame, frame = cap.read()  # frame is (height, width, channels)
        if cur_frame in video_range:
            cv2.imshow('frame', frame)
            # print frame.shape
            # assert False
            writer.write(frame)
            k = cv2.waitKey(30) & 0xff
            if k == 27:
                break
        cur_frame += 1

    # clean up
    cv2.destroyAllWindows()
    cap.release()
    writer.release()


if __name__ == '__main__':
    a = Video_Tag_Extract(video_name='ad.mp4')
    a.extract_images_from_video(video_start=0, video_end=2000, job_id=1)
    # a.analyze_images(job_id=1)
    # a.remove_images(video_start=700, video_end=700+self.api.get_info()['max_batch_size'], job_id=1)

