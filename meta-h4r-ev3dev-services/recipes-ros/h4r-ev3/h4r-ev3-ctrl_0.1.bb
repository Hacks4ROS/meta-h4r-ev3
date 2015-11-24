DESCRIPTION = "A node for controlling the various stuff on the LEGO EV3 platform"
SECTION = "devel"

DEPENDS = "catkin roscpp sensor-msgs nav-msgs geometry-msgs std-msgs roslib tf diagnostic-msgs ev3dev-lang-cpp image-transport cv-bridge hardware-interface h4r-ev3-joint-setup"

ROS_SPN = "h4r_ev3_ctrl" 

require h4r-ev3_0.1.inc