
import torch

from kalmanfilter import KalmanFilter
from tracker import Tracker
import cv2

# from deep_sort.deep_sort import DeepSort;

model = torch.hub.load('.', 'custom', path='weights/best.pt', source='local')
# model = torch.hub.load('ultralytics/yolov5', 'yolov5s')  # or yolov5n - yolov5x6, custom

cap = cv2.VideoCapture("30s.mp4")

frame_width = int(cap.get(cv2.CAP_PROP_FRAME_WIDTH))
frame_height = int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT))
fps = int(cap.get(cv2.CAP_PROP_FPS))

fourcc = cv2.VideoWriter_fourcc(*'DIVX')
output = cv2.VideoWriter('output4.avi', fourcc, fps, (frame_width, frame_height))

# Kalman Filter
# kf = KalmanFilter()

# Object Tracking
tracker = Tracker()
# initialize deepsort

#outTxt = open('outTxt.txt' ,'x')
#linha = outTxt.readlines()
results_point = []
results_point_float = []
results_point_float2 = []
results_point_predict = []

dic_keysTracker = {}

obs = []
f: int = 0

while True:
    ret, frame = cap.read()

    if ret is False:
        break
    else:

        frame = cv2.resize(frame, (1080, 720))
        results = model(frame)

        box = []
        for index, row, in results.pandas().xyxy[0].iterrows():

            x1 = float(row['xmin'])
            y1 = float(row['ymin'])
            x2 = float(row['xmax'])
            y2 = float(row['ymax'])

            box.append([x1, x2, y1, y2])


        box_ids = tracker.update(box)

        box_2 = []
        for box_id in box_ids:
            x, y, w, h, id= box_id

            cx = int((x + y) / 2)
            cy = int((w + h) / 2)

            cx_float = (x + y) / 2
            cy_float = (w + h) / 2

            # predicted = kf.predict(cx, cy)

            results_point.append([cx, cy, id])
            results_point_float.append([cx_float, cy_float, id])
            results_point_float2.append([cx_float, cy_float, id])

            box_2.append([cx, cy, id])

            # cv2.circle(frame, (cx, cy), 1, (255, 0, 0), 4)
            # cv2.putText(frame, "ID: " + str(id), (cx, cy+2), cv2.FONT_HERSHEY_SIMPLEX, 0.9, (0, 255, 0), 3)
        nxa = 0
        nya = 0

        axi = 0
        ayi = 0

        box_3 = []
        for pts1 in results_point:

            x, y, id = pts1



            for pts2 in box_2:
                xi ,yi ,ida = pts2

                if nxa == 0:nxa = x
                if nya == 0:nya = y
                if axi == 0:xi = x
                if ayi == 0:yi = y


                if( id == ida):
                    results_point_predict.append([x, y, id])

                    nxa = x
                    nya = y
                    axi = xi
                    ayi = yi



        print(f)
        f+=1


cap.release()
output.release()

cv2.destroyAllWindows()
list_ids = []
for pts in results_point_predict:
    x,y,id = pts

    if not(list_ids.__contains__(id)):
        list_ids.append(id)
f = 0
arquivo = open('file.txt', 'w')



for id_point in list_ids:
    list = []
    for pts in results_point_predict:
        x,y,id = pts
        if id_point == id:
            list.append([x,y])

    for pts in list:
        x, y = pts
        s = str(id_point)+','+str(x)+','+str(y)+'\n'
        arquivo.write(s)



    print(f)
    f+=1
#s = str(id)+' '+str(x)+' '+str(y)


