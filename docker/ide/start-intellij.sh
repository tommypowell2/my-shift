#!/bin/bash  

XADD="$(cat /opt/dockermount/xadd)"

xauth add $XADD

./idea-IC-171.4249.39/bin/idea.sh



