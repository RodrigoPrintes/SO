import torch

from kalmanfilter import KalmanFilter
from tracker import Tracker
import cv2

model = torch.hub.load('.', 'custom', path='weights/best.pt', source='local')
# model = torch.hub.load('ultralytics/yolov5', 'yolov5s')  # or yolov5n - yolov5x6, custom

cap = cv2.VideoCapture("30s.mp4")

frame_width = int(cap.get(cv2.CAP_PROP_FRAME_WIDTH))
frame_height = int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT))
fps = int(cap.get(cv2.CAP_PROP_FPS))

fourcc = cv2.VideoWriter_fourcc(*'DIVX')
output = cv2.VideoWriter('output.avi', fourcc, fps, (frame_width, frame_height))


video_cod = cv2.VideoWriter_fourcc(*'XVID')
fps = int(cap.get(cv2.CAP_PROP_FPS))
video_output = cv2.VideoWriter('out_video.avi',
                               video_cod,
                               fps,
                               (1020, 720))
# Kalman Filter
# kf = KalmanFilter()

# Object Tracking
tracker = Tracker()

results_point = []
results_point_predict = []
obs = []
f: int = 0

while True:
    ret, frame = cap.read()

    if ret is False:
        break
    else:
        results = model(frame)

        print(f)
        f += 1
        output.write(frame)
        video_output(frame)

cap.release()
output.release()
