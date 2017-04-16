#!/bin/bash

mainClass=AppAdminWebRun;
pinfo=$(jps|grep $mainClass)
echo "$pinfo";
if [ -n "$pinfo" ];
  then
    pid=${pinfo%%AppAdminWebRun}; 
    if [ -n "$pid" ];
      then
        expr $pid "+" 10 &> /dev/null;
        if [ $? -eq 0 ]; then
          ans=$(kill -9 $pid);
          if [ -n "$ans" ];
            then
              echo "Some error? when kill pid = $pid";
            else
              echo "Kill pid = $pid success!";
          fi
        fi
      else
        echo "Pid is empty! pid = $pid";
    fi
  else
    echo "Stop failed, AppAdminWebRun is not exist!";
fi

