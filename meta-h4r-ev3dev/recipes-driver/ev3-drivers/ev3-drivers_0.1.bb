DESCRIPTION = "This installs the Lego Interface Drivers from ev3dev for Lego Mindstorms EV3" 
SECTION = "services" 
LICENSE = "CLOSED" 
PR = "r0" 
DEPENDS = ""

SRC_URI = "git://github.com/ev3dev/lego-linux-drivers.git;protocol=git;branch=master"
SRCREV="1c8b6afc9e45e4f19bf6cc30b6279d16631fb20f"

#TODO fix this to compile