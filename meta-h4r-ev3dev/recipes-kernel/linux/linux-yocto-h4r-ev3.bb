inherit kernel
require recipes-kernel/linux/linux-yocto.inc

SRC_URI = "gitsm://github.com/Hacks4ROS-forks/ev3-kernel.git;protocol=git;branch=ev3dev-jessie\
           file://defconfig \
           file://logo_h4r_ev3_mono.pbm \
           "

SRC_URI += "\
            file://0001-add-h4r_ev3-logo.patch \
            file://meta-h4r-ev3dev.scc \
            file://meta-h4r-ev3dev.cfg \
            file://meta-h4r-ev3dev-user-config.cfg \
            file://meta-h4r-ev3dev-user-patches.scc \
           "
           

LINUX_VERSION ?= "3.16.7"
LINUX_VERSION_EXTENSION ?= "-h4r-ev3dev"

SRCREV="d18d681e8667139353eac364e460a70ba29c9559"

PR = "r0"
PV = "${LINUX_VERSION}+git${SRCPV}"

SYSLINUX_SERIAL_TTY ?= "console=ttyS1,115200n8"
COMPATIBLE_MACHINE_meta-h4r-ev3dev = "ev3dev"

S = "${WORKDIR}/git"


do_patch_append() {
    mv ${WORKDIR}/logo_h4r_ev3_mono.pbm ${S}/drivers/video/logo/
}