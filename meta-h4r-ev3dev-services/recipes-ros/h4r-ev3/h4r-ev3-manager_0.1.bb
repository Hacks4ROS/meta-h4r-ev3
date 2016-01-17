DESCRIPTION = "A managing node for the EV3"
SECTION = "devel"

DEPENDS =  "catkin roscpp sensor-msgs nav-msgs geometry-msgs std-msgs roslib tf diagnostic-msgs ev3dev-lang-cpp image-transport cv-bridge controller-manager hardware-interface h4r-ev3-control"

ROS_SPN = "h4r_ev3_manager" 

require h4r-ev3_0.1.inc