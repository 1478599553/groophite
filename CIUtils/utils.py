from copy import copy
from os import makedirs, removedirs


from shutil import copyfile
import sys


def preGetModVersion():
    src_file = open(r"./src/main/java/com/draming/groophite/groophite.java","r")
    src = src_file.read()
    return src.split("String VERSION")[1].split(r'"',1)[1].split(r'"',1)[0]

def postGetModVersion():
    src_file = open(r"./groophite.java","r")
    src = src_file.read()
    return src.split("String VERSION")[1].split(r'"',1)[1].split(r'"',1)[0]

if (sys.argv[1] == "postBuild"):
    makedirs("./artifacts/groophite/112/"+postGetModVersion())
    copyfile("./groophite-release.zip","./artifacts/groophite/112/"+postGetModVersion()+"/groophite_mc112_"+postGetModVersion()+".zip")

if (sys.argv[1] == "preBuild"):
    removedirs("./.git")
    gradleFile = open("./build.gradle","r")
    gradleContent =  gradleFile.readlines()
    gradleFile.close()
    i = 0
    for line in gradleContent:
        if line.endswith("VersionMark"):
            gradleContent[i] = "version = "+"'"+preGetModVersion+"'"
            break
        i = i + 1
    writeGradleFile = open("./build.gradle","w")
    writeGradleFile.writelines(gradleContent)
    writeGradleFile.close()

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