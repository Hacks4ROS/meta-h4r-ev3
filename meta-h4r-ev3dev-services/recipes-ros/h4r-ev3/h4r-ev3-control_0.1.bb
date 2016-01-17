DESCRIPTION = "ROS Control for the EV3 Sensors and base class to interact with the Lego drivers"
SECTION = "devel"

DEPENDS = "catkin roscpp sensor-msgs h4r-ev3-msgs nav-msgs geometry-msgs std-msgs roslib tf diagnostic-msgs ev3dev-lang-cpp image-transport cv-bridge hardware-interface"

ROS_SPN = "h4r_ev3_control" 

require h4r-ev3_0.1.inc