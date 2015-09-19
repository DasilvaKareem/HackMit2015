import cv2
import os
import numpy as np

def extract_tags():
    """
        vacation.mp4 (https://www.youtube.com/watch?v=jbWQG5I0iGI)
            - videosegments
                - 

    """
    working_root = '../'
    videos_root = os.path.join(working_root, 'training_videos')
    video_file_name = 'vacation.mp4'

    cap = cv2.VideoCapture(os.path.join(videos_root, 'vacation.mp4'))
    num_frames = cap.get(cv2.cv.CV_CAP_PROP_FRAME_COUNT)

    cur_frame = 0
    while cur_frame < num_frames:
        print i 
        has_frame, frame = cap.read()  # frame is (height, width, channels)
        cv2.imshow('frame', frame)
        k = cv2.waitKey(30) & 0xff
        if k == 27:
            break
        cur_frame += 1

    cv2.destroyAllWindows()
    cap.release()



# import numpy as np
# import cv2
# import copy

# cap = cv2.VideoCapture('v_ShavingBeard_g01_c02.avi')
# num_frames = cap.get(cv2.cv.CV_CAP_PROP_FRAME_COUNT)

# # params for ShiTomasi corner detection
# feature_params = dict( maxCorners = 100,
#                        qualityLevel = 0.3,
#                        minDistance = 7,
#                        blockSize = 7 )

# # Parameters for lucas kanade optical flow
# lk_params = dict( winSize  = (15,15),
#                   maxLevel = 2,
#                   criteria = (cv2.TERM_CRITERIA_EPS | cv2.TERM_CRITERIA_COUNT, 10, 0.03))

# # Create some random colors
# color = np.random.randint(0,255,(100,3))
# cur_frame = 0

# # Take first frame and find corners in it
# ret, old_frame = cap.read() #ret is a boolean that is true if there is a next rectangle, and old_frame is a numpy nd array
# cur_frame += 1
# old_gray = cv2.cvtColor(old_frame, cv2.COLOR_BGR2GRAY)
# p0 = cv2.goodFeaturesToTrack(old_gray, mask = None, **feature_params) #We'd have to replace this with our desired features
# # p0 is a (numPoints, 1, 2) numpy ndarray, where 2 is because we are in 2d

# # Create a mask image for drawing purposes
# mask = np.zeros_like(old_frame)

# while(cur_frame < num_frames):
#     ret,clean_frame = cap.read()
#     cur_frame += 1
#     frame_gray = cv2.cvtColor(clean_frame, cv2.COLOR_BGR2GRAY)

#     # calculate optical flow
#     p1, st, err = cv2.calcOpticalFlowPyrLK(old_gray, frame_gray, p0, None, **lk_params)

#     frame = copy.copy(clean_frame) #this should be a deep copy!

#     # Select good points
#     good_new = p1[st==1]
#     good_old = p0[st==1]

#     # draw the tracks
#     for i,(new,old) in enumerate(zip(good_new,good_old)):
#         a,b = new.ravel() #coordinates of the new point
#         c,d = old.ravel() #coordinates of the old point
#         cv2.line(mask, (a,b),(c,d), color[i].tolist(), 2)
#         cv2.circle(frame,(a,b),5,color[i].tolist(),-1)

#     img = cv2.add(frame,mask)
#     # img = cv2.resize(img, (0,0), fx=1.0, fy=1.0); #// to fifth size or even smaller

#     cv2.imshow('frame',img)
#     k = cv2.waitKey(30) & 0xff
#     if k == 27:
#         break

#     # Now update the previous frame and previous points
#     old_gray = frame_gray.copy()
#     p0 = good_new.reshape(-1,1,2)

# cv2.destroyAllWindows()
# cap.release()
