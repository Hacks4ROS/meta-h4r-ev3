#DESCRIPTION = "This installs the brickman systemd-services" 
SECTION = "services" 
LICENSE = "CLOSED" 
PR = "r0" 

SRC_URI = "git://github.com/ev3dev/ev3-systemd.git;branch=ev3dev-jessie"
SRCREV="c78323a6017e1e7600a4ead3bf2be669bda5bd48"

S = "${WORKDIR}/git"

do_install() {	
	install -d ${D}/lib/systemd/system
    for f in ${S}/systemd/*; do
		install -m 0644 $f ${D}/lib/systemd/system
	done
	
	install -d ${D}/lib/udev/rules.d/
	install -m 0644 ${S}/debian/ev3.udev ${D}/lib/udev/rules.d/60-udev.rules
}

FILES_${PN} += "\
/lib/systemd/system/cdc-gadget.service \
/lib/systemd/system/console-setup.service \
/lib/systemd/system/ev3-bluetooth.service \
/lib/systemd/system/ev3-global-cursor-default.service \
/lib/systemd/system/ev3-leds.service \
/lib/systemd/system/ev3-uart@.service \
/lib/systemd/system/rndis-gadget.service \
/lib/udev/rules.d/60-udev.rules \
"
