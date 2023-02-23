import cv2

outTxt = open('file.txt' ,'r')

img = cv2.imread("blue_background.webp")
linhas = outTxt.read()

linh = linhas.split('\n')
id = 0
l = []

list_ids = []
for s in linh:
    n = s.split(',')

    #
    if n[0] != '':
        if int(n[0]) == id:
            if not (list_ids.__contains__(int(n[0]))):
                list_ids.append(int(n[0]))
            if n.__len__() >= 2:

                l.append([int(n[0]),int(n[1]), int(n[2])])

        else:
            id+=1
l_semrepetidos =[]
for pts in l:
    if not (l_semrepetidos.__contains__(pts)):
        l_semrepetidos.append(pts)


for ids in list_ids:
    copy = img.copy()
    for pts in l_semrepetidos:
        x,y,id = pts
        if ids == 0:
            cv2.circle(copy, (x, y), 1, (255, 0, 0), 5)
            print(id,x,y)



cv2.imwrite('./resultsImg/'+str(0)+'.webp', copy)

# salvar a imagem copiada
#cv2.imwrite('resultsImg/copy.webp', imagem_copia)
#cv2.imwrite('resultsImg/copy2.webp', imagem_copia2)