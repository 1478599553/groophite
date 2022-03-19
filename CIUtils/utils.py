from copy import copy
from os import mkdir
from shutil import copyfile
import sys


def getModVersion():
    src_file = open(r"./src/main/java/com/draming/groophite/groophite.java","r")
    src = src_file.read()
    print(src.split("String VERSION")[1].split(r'"',1)[1].split(r'"',1)[0])
if (sys.argv[1] == "postBuild"):
    mkdir("./artifacts/groophite/"+getModVersion())
    copyfile("./groophite-release.zip","./artifacts/groophite/"+getModVersion()+"/groophite_mc112_"+getModVersion()+".zip")

tempWorkFlow = '''
          
          mkdir ./buildMod
          cd ./buildMod
          git clone https://github.com/1478599553/groophite.git
          cd ./groophite
          python ./buildMod/groophite/CIUtils/utils.py
          ./gradlew.bat build

          ./CIUtils/7z a ./build/groophite-release.zip ./build/libs/
          Copy ./build/groophite-release.zip D:/groophite.zip
          cd D:/buildMod
          git clone https://github.com/1478599553/artifacts.git
          python D:/buildMod/groophite/CIUtils/utils.py postBuild
          cd D:/buildMod/artifacts
          git add -A
          git commit -m "autobuild"
          git push origin main
          '''