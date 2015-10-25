#DESCRIPTION = "This installs the brickman for Lego Mindstorms EV3" 
SECTION = "services" 
LICENSE = "CLOSED" 
PR = "r0" 
DEPENDS = "glib-2.0 vala libgee libgrx ncurses libpng jpeg-native libgudev"


SRC_URI = "gitsm://github.com/ev3dev/brickman.git;protocol=git;branch=ev3dev-jessie\
           file://disable_gir.patch\
           file://add_libpng.patch\
           file://remove_valadoc.patch\
           file://brickman.service \
           "
           
SRCREV="f7662b34bc6989f87cdd4c4c73a7235f14d0f257"
SRC_URI[md5sum] = "797c8c3023a810bb8c11ea0a0d185855"
SRC_URI[sha256sum] = "359d5e3d8981c5047364a3fe5cf7c0928b796570f7cf74aa3ed01593942fd064"

S = "${WORKDIR}/git"


EXTRA_OECMAKE="\
    -DBRICKMAN_TEST=OFF \
    -DGSETTINGS_COMPILE=OFF \
    -DEV3DEVKIT_DESKTOP=OFF \
"

EXTRA_OECONF=""
EXTRA_OEMAKE = "'CC=${CC}' 'LD=${LD}' 'RANLIB=${RANLIB}' 'AR=${AR}' \
        'CFLAGS=${CFLAGS} -I${S}/include' 'BUILDDIR=${S}'"
 

inherit pkgconfig cmake vala pkgconfig

do_install_append() {
	mkdir -p ${D}/lib/systemd/system/
	mkdir -p ${D}/etc/systemd/system/
	
	install -m 0644 ${WORKDIR}/brickman.service ${D}/lib/systemd/system
	
	ln -s /lib/systemd/system/brickman.service ${D}/etc/systemd/system/display-manager.service
}


FILES_${PN} += "\
	/lib/systemd/system/brickman.service \
"