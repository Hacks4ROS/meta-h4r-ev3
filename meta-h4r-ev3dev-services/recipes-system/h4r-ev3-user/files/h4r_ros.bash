
export H4R_ROS_MASTER_FILE="/home/$USER/.ros_master"
export H4R_ROS_IP_FILE="/home/$USER/.ros_ip"

function load_ros_master_ip
{
    if [ -e $H4R_ROS_IP_FILE ]; then
         export ROS_IP=`cat $H4R_ROS_IP_FILE`
    else
         unset ROS_IP
    fi
    
    if [ -e  $H4R_ROS_MASTER_FILE ]; then
        export ROS_MASTER_URI=`cat $H4R_ROS_MASTER_FILE`
    else
        export ROS_MASTER_URI=http://localhost:11311
    fi
}

function ros_master_set
{
    #$1 hostname
    #$2 port

    host=$1
    port=$2

    if [ "$host" == "" ]; then
        host=localhost
    fi
    
    if [ "$port" == "" ]; then
        port="11311"
    fi
    
    uri="http://$host:${port}"
    
    echo "ROS_MASTER_URI set to $uri"
    echo "$uri" > $H4R_ROS_MASTER_FILE  
    export ROS_MASTER_URI=$uri
    return 0
}

function ros_ip_set
{
  RED="\e[37;41m"
  GREEN="\e[30;42m"
  YELLOW="\e[30;43m"
  BOLD="\e[1m"
  NOCOLOR="\e[0m"
        
  if [ "$1" == "" ]; then
     unset ROS_IP
     rm -rf $H4R_ROS_IP_FILE 
     echo -e "${YELLOW}ROS_IP unset${NOCOLOR}"
     return 0
  fi
      
  #$1 network interface
  #$2 report on success [default - 1]
  #$3 report on error   [default - 1]


  {
  ifconfig $1
  } &> /dev/null
  
  if [ "$?" -ne 0 ]; then
    echo -e "${RED}Interface ${BOLD}$1${NOCOLOR}${RED} not found! ${BOLD}ROS_IP not set!${NOCOLOR}"
    return 1
  fi
  ip_addr=`ifconfig $1 | grep 'inet addr:' | cut -d: -f2 | awk '{print $1}'`

  if [ -z "$ip_addr" ]; then
    if [ "$3" != "0" ]; then
      echo -e "${RED}Interface ${BOLD}$1${NOCOLOR}${RED} not connected, ${BOLD}ROS_IP not set!${NOCOLOR}"
    fi
    return 1
  fi
  
  if [ "$2" != "0" ]; then
    echo -e ${GREEN}ROS_IP set to $1 with IP $ip_addr${NOCOLOR}
  fi
  
  
  export ROS_IP=$ip_addr
  echo "$ip_addr" > $H4R_ROS_IP_FILE
  
  return 0
}

# Display welcome message
function greet
{

LINE_END="\x1B[K"
EMPTY_LINE="\e[0;30;47m\x1B[K"


clear
echo
echo -e "\e[0m"
echo -e "\e[0;30;47m""Welcome to ""\e[3;30;47mHacks""\e[0;31;47m4""\e[3;30;47m""ROS EV3""\e[3;30;47m yocto""\e[0;34;47m""\xE2\x97\x8F""\e[0;30;47m"
echo -e $EMPTY_LINE
echo -e "\e[0;30;47m(http://github.com/Hacks4ROS)\e[0;30;47m${LINE_END}"
echo -e $EMPTY_LINE
echo -e "\e[0;30;47m"Version: "\e[5;31;47m""\xCE\xB1""\e[0;30;47m${LINE_END}"
echo -e $EMPTY_LINE
echo -e "\e[04;30;47m"Important Variables${LINE_END}
echo -e $EMPTY_LINE
echo -e "\e[00;30;47m"Hostname: "\e[0;34;47m"$HOSTNAME${LINE_END}


if [ "$ROS_MASTER_URI" != "" ]; then
PRINT_ROS_MASTER="\e[0;34;47m"$ROS_MASTER_URI"\e[00;30;47m"${LINE_END}
else
PRINT_ROS_MASTER="\e[5;31;47m"UNSET"\e[00;30;47m"${LINE_END}
fi
echo -e "\e[00;30;47m"ROS Master URI: "\e[0;34;47m"$PRINT_ROS_MASTER${LINE_END}


if [ "$ROS_IP" != "" ]; then
PRINT_ROS_IP="\e[00;34;47m"$ROS_IP"\e[00;30;47m"${LINE_END}
else
PRINT_ROS_IP="\e[01;33;47m"unset"\e[00;30;47m"${LINE_END}
fi

echo -e "\e[00;30;47m"ROS IP: "\e[0;34;47m"$PRINT_ROS_IP${LINE_END}  


echo -e $EMPTY_LINE
echo -e "\e[04;30;47m"Useful Commands"\e[04;30;47m"${LINE_END}
echo -e $EMPTY_LINE
echo -e "\e[00;30;47m"ev3_manager - EV3 ROS node ${LINE_END}
echo -e "\e[00;30;47m""ros_ip_set [IP (none unsets)] - Sets ROS_IP" ${LINE_END}
echo -e "\e[00;30;47m""ros_master_set [host] [port(optional)] - Sets ROS_MASTER_URI" ${LINE_END}
echo -e "\n\e[0m"
return 0
}

alias ev3_manager="/opt/ros/indigo/lib/h4r_ev3_manager/ev3_manager_node"