#DESCRIPTION = "This installs the binary firmware from a Debian package to provide firmware for the Bluetooth chip and the Programmable Runtime Unit (PRU) of the LEGO MINDSTORMS EV3 hardware." 
SECTION = "firmware" 
LICENSE = "CLOSED" 
PR = "r0" 

SRC_URI = "git://github.com/ev3dev/firmware-ev3.git;branch=ev3dev-jessie"
SRCREV="4e08f91177e2c0623aaa52626fb8e4ad43099cb1"

S = "${WORKDIR}/git"

do_install() {	
    install -d ${D}/lib
	install -d ${D}/lib/firmware
	install -d ${D}/lib/firmware/ti-connectivity
	
	install -m 0644 ${S}/firmware/PRU_SUART.bin ${D}/lib/firmware/PRU_SUART.bin
	install -m 0644 ${S}/firmware/ti-connectivity/TIInit_6.2.31.bts ${D}/lib/firmware/ti-connectivity/TIInit_6.2.31.bts
	install -m 0644 ${S}/firmware/ti-connectivity/TIInit_6.6.15.bts ${D}/lib/firmware/ti-connectivity/TIInit_6.6.15.bts
}

FILES_${PN} += "\
/lib/firmware/PRU_SUART.bin \
/lib/firmware/ti-connectivity/TIInit_6.2.31.bts \
/lib/firmware/ti-connectivity/TIInit_6.6.15.bts \
"