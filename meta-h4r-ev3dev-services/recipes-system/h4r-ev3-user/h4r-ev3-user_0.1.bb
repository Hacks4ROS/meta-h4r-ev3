DESCRIPTION = "Scripts for easier usability of H4R EV3"
HOMEPAGE = "http://github.com/Hacks4ROS"
SECTION = "scripts"
LICENSE = "LGPLv2.0"
LIC_FILES_CHKSUM = "file://copying;md5=8ca43cbc842c2336e835926c2166c28b"
INC_PR = "r0"
PE = "1"
PR = "${INC_PR}.2"


inherit pkgconfig

SRC_URI = "\
           file://h4r_scripts.bash \
           file://rosenv.bash \
           "
           
S = ${WORKDIR}
           
do_configure() {

}


do_install() 
{    
    install -d ${D}${sysconfdir}/h4r_ev3_scripts
    
    for f in ${S}/*; do
        install -m 0644 $f ${D}${sysconfdir}/h4r_ev3_scripts
    done
}

FILES_${PN} += "\
                ${sysconfdir}/h4r_ev3_scripts/h4r_scripts.bash \
                ${sysconfdir}/h4r_3v3_scripts/rosenv.bash \
               "