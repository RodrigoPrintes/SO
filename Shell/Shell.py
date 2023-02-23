import os
import shutil

import subprocess


class Shell:  
    
   

    def __init__(self, root_path):
       
        self.root_path = root_path
        self.process = 0
        if os.path.exists('log.txt'):
           
            posR = open('log.txt','r')
            pos = posR.read()
            strings = pos.split('\n')
            if pos.__len__() > 1:
                
                lastCommand  = strings[strings.__len__()-2]

                self.callWrite = int(lastCommand[0])
            else:
                self.callWrite = 0
        else:
            self.log = open('log.txt','x')
            self.callWrite = 0
       
    def ls(self):
        #pasta = self.root_path
        #esta mostrado diretorios desnecessarios

        return os.listdir('./')

    def pwd(self):
        return self.root_path

    def cd(self, path):
     
        if path.__len__() == 1:
            os.chdir('/')
            self.root_path = os.getcwd()
        else:
            try:
                os.chdir(path[1])
                self.root_path = os.getcwd()
       
            except:
                print("cd: "+path[1]+": No such file or directory")

    def cp(self, orig, dest):
        try:
            shutil.copy( orig , dest )
        except:
            print('cp: cannot stat '+orig+': No such file or directory')

    def mv(self, orig, dest):
        try:
            if orig == 'log.txt' or orig == 'help':
                print('no permission to remove this file press help for information')
            else:
                shutil.move( orig , dest ) 
        except:
            print('mv: cannot stat '+orig+': No such file or directory')      
          
    def rm(self,arq='', file=''):
        if os.path.exists(file):
            if arq == '':
                if os.path.isfile(file):
                    if file == 'log.txt' or file == 'help.txt':
                        print('no permission to remove file \npress help for information')
                    else:
                        os.remove(file)

                elif os.path.isdir(file):
                    print('rm: cannot remove '+file+': Is a directory')
                else:
                    print('rm: cannot remove '+file+': No such file or directory')
            else:
                if arq == '-r':
                    shutil.rmtree(file)
        else:
            print('rm: cannot remove '+file+': No such file or directory')
    
    def mkdir(self,pasta):
        if os.path.isdir(pasta):
            print ('mkdir: cannot create directory "'+pasta+'": File exists')
        else:
            os.mkdir(pasta)

    def uname(self):
        return os.uname() 
    
    def rename(self,orig,dest):
        if os.path.exists(orig):
            os.rename(orig,dest)
        else:
            print('No such file or directory')

    def cat(self,arq):
        file = open(arq,'r')
        lines  = file.read()
        return lines

    def testScript(self,script):
        if  ( script[script.__len__()-3] == '.'):
            if script[script.__len__()-2] == 'p' and script[script.__len__()-1] == 'y':
                return True
            else:
                return False
        else:
                return False

    def exec(self,scrip):
        try:
            exec(open(scrip).read())

        except:
            print('error ao execultar o script')

    def writeLog(self,string):
        self.callWrite+=1
        self.log= open("log.txt", "a")
        s = str(self.callWrite)+"  "+string+"\n"
        self.log.write(s)

    def history(self):
        return self.cat('log.txt')

    def grep(self, words, arquivo):
        out = []
        try:
           
            strings = self.readStrings(arquivo)
            for word in strings:
                st = word.split(' ')
                for item in st:
                    if  words == item:
                        out.append(words)   
            
            return out
        except:
            return out


        
    def readStrings(self,arquivo):
        posR = open(arquivo,'r')
        pos = posR.readlines()
        return pos

   



if __name__ == "__main__":
    shell  = Shell( os.getcwd())
   
    print("-- Welcome to Shell \nPress -help for Commands or q to out--\n")
    s = ' '
    while(s != 'exit'):
        s = input(""+shell.pwd()+"# ") 

        s1 = s.split(' ')
        if s1.__len__() == 1:
            shell.writeLog(s)
            if s1[0] == "ls":
                for item in shell.ls():
                    print(item)
                
            elif s1[0] == "pwd":
                print(shell.pwd())
               
            elif s1[0] == "uname":
               
                print(shell.uname())
               
            elif s1[0] == 'help':
                print('needs help')

            elif s1[0] == 'history':
                print(shell.history())
            elif s1[0] == 'exit':
                pass
            else:
                print(s+": command not found")

        else:
            shell.writeLog(s)
            if s1[0] == 'cd':
                
                if( s1.__len__() == 2):
                    shell.cd(s1)
                
                else:
                    print(s+": command not found")

            elif s1[0] == 'cp':
               
                if( s1.__len__() == 3):
                    shell.cp(s1[1],s1[2])
                
                else:
                    print(s+": command not found")

            elif s1[0] =='mv':
                
                if( s1.__len__() == 3):
                    shell.mv(s1[1],s1[2])
                else:
                    print(s+": command not found")

            elif s1[0] == 'rm':
              
                if s1.__len__() == 2: 
                    shell.rm(file = s1[1])
                elif( s1.__len__() == 3):
                    shell.rm(s1[1],s1[2])
                else:
                    print(s+": command not found")

            elif s1[0] == 'mkdir':
               
                if s1.__len__() == 2:
                    shell.mkdir(s1[1])
                else: 
                    'mkdir: missing operand\nTry "help" for more information.'
            
            elif s1[0] == 'rename':
                if s1.__len__() == 3:
                    shell.rename(s1[1],s1[2])
                else: 
                    'rename error: Try "help" for more information.'

            elif s1[0] == 'cat':
                if s1.__len__() == 2:
                    print(shell.cat(s1[1]))
                else: 
                    'cat error: Try "help" for more information.'
            
            elif s1[0] == './':
                if s1.__len__() == 2:
                   shell.exec(s1[1])
            
            elif s1[0] == 'grep':
                if s1.__len__() == 3:
                   print(shell.grep(s1[1],s1[2]))

            else:
                print(s+": command not found")
            
        
    
     

