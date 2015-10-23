inherit kernel
require recipes-kernel/linux/linux-yocto.inc

SRC_URI = "gitsm://github.com/Hacks4ROS-forks/ev3-kernel.git;protocol=git;branch=ev3dev-jessie\
           file://defconfig"

SRC_URI += "file://meta-h4r-ev3dev.scc \
            file://meta-h4r-ev3dev.cfg \
            file://meta-h4r-ev3dev-user-config.cfg \
            file://meta-h4r-ev3dev-user-patches.scc \
           "


LINUX_VERSION ?= "3.16.7"
LINUX_VERSION_EXTENSION ?= "-h4r-ev3dev"

SRCREV="${AUTOREV}"

PR = "r0"
PV = "${LINUX_VERSION}+git${SRCPV}"

SYSLINUX_SERIAL_TTY ?= "console=ttyS1,115200n8"
COMPATIBLE_MACHINE_meta-h4r-ev3dev = "ev3dev"
