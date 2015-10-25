#DESCRIPTION = "This installs the brickman systemd-services" 
SECTION = "services" 
LICENSE = "CLOSED" 
PR = "r0" 

SRC_URI = "git://github.com/ev3dev/ev3-systemd.git;branch=ev3dev-jessie"
SRCREV="c78323a6017e1e7600a4ead3bf2be669bda5bd48"

S = "${WORKDIR}/git"

do_install() {	
	mkdir -p ${D}/lib/udev/rules.d
	mkdir -p ${D}/etc/systemd/system/getty.target.wants
	mkdir -p ${D}/etc/systemd/system/multi-user.target.wants
	mkdir -p ${D}/etc/systemd/system/network.target.wants
	
	install -d ${D}/lib/systemd/system
    for f in ${S}/systemd/*; do
		install -m 0644 $f ${D}/lib/systemd/system
	done
	
	install -d ${D}/lib/udev/rules.d/
	install -m 0644 ${S}/debian/ev3.udev ${D}/lib/udev/rules.d/60-udev.rules


	ln -s libev3dev.so.1.0 ${D}/lib/libev3dev.so.1
	ln -s libev3dev.so.1   ${D}/lib/libev3dev.so

	ln -s /lib/systemd/system/network.target.wants/cdc-gadget.service ${D}/etc/systemd/system/network.target.wants/cdc-gadget.service
	ln -s /lib/systemd/system/network.target.wants/rndis-gadget.service ${D}/etc/systemd/system/network.target.wants/rndis-gadget.service
	ln -s /lib/systemd/system/console-setup.service ${D}/etc/systemd/system/getty.target.wants/console-setup.service
	ln -s /lib/systemd/system/ev3-global-cursor-default.service ${D}/etc/systemd/system/getty.target.wants/ev3-global-cursor-default.service
	ln -s /lib/systemd/system/ev3-leds.service ${D}/etc/systemd/system/multi-user.target.wants/ev3-leds.service

	#Disable serial gettys
	ln -s /dev/null ${D}/etc/systemd/system/serial-getty@ttyS0.service
	ln -s /dev/null ${D}/etc/systemd/system/serial-getty@ttyS1.service
	ln -s /dev/null ${D}/etc/systemd/system/serial-getty@ttyS2.service
	ln -s /dev/null ${D}/etc/systemd/system/serial-getty@ttySU0.service
	ln -s /dev/null ${D}/etc/systemd/system/serial-getty@ttySU1.service
}

FILES_${PN} += "\
/lib/systemd/system/cdc-gadget.service \
/lib/systemd/system/rndis-gadget.service \
/lib/systemd/system/console-setup.service \
/lib/systemd/system/ev3-global-cursor-default.service \
/lib/systemd/system/ev3-leds.service \
/lib/systemd/system/ev3-bluetooth.service \
/lib/systemd/system/ev3-uart@.service \
/lib/udev/rules.d/60-udev.rules \
"
