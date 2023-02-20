import os


class Shell:  
    commands = []
    pastas = []

    def __init__(self, root_path):
        self.commands = ["cd","cp","mv","rm","mkdir","rename","pwd","ls","help","./","~","&"]
        self.root_path = root_path
        for diretorio, subpastas, arquivos in os.walk('./'):
            self.pastas.append(subpastas)

    def ls(self):
        #pasta = self.root_path
        
        arq = []
        out = []
        for diretorio, subpastas, arquivos in os.walk('./'):
            arq.append((arquivos))
           
            self.pastas.append(subpastas)
          
          
        for a in arq[0]:
            
            out.append(a)  

        for p in self.pastas[0]:
            p="/"+p
            out.append(p)  
      

        for outs in out :
            print(outs)
 

    def pwd(self):
        return self.root_path

    def cd(self, path):
     
        if path.__len__() == 1:
            root = self.root_path.split('/')
            print(root)
        else:
            #if self.pastas.__contains__(path[1]):
            try:
                os.chdir(path[1])
                #ok  = True
                self.root_path = os.getcwd()
                
          
            except:
                print("cd: "+path[1]+": No such file or directory")

    def keypress(event):
        if event.keysym == 'Escape':
            keyPressed = event.char 
            print ("Você pressionou: " + keyPressed)
          

if __name__ == "__main__":
    shell  = Shell( os.getcwd())
   
    print("-- Welcome to Shell \nPress -help for Commands or q to out--\n")
    s = ' '
    while(s != 'q'):
        s = input(""+shell.pwd()+"# ") 

        shell.keypress()

        if s == "ls":
            shell.ls()
        elif s == "pwd":
                print(shell.pwd())
        
        elif s[0] == 'c' and s[1]== 'd':
            s1 = s.split(' ')
            if( s1.__len__() < 3):
                shell.cd(s1)
            else:
                print(s+": command not found")
        
        elif s == 'q':
            pass
        else:
            print(s+": command not found")
        
    
     

