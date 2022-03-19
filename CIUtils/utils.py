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