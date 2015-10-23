DESCRIPTION = "GRX is a 2D graphics library originaly written by Csaba Biegl for DJ Delorie's DOS port of the GCC compiler."
HOMEPAGE = "http://grx.gnu.de"
SECTION = "libs"
LICENSE = "LGPLv2.0"
LIC_FILES_CHKSUM = "file://copying;md5=8ca43cbc842c2336e835926c2166c28b"
INC_PR = "r0"
PE = "1"
PR = "${INC_PR}.2"


inherit pkgconfig

DEPENDS = "coreutils popt glib-2.0 jpeg-native zlib lzo e2fsprogs util-linux libpng jpeg-native"

SRC_URI = "http://grx.gnu.de/download/grx249.tar.gz\
           file://remove_m32_flags.patch\
           file://remove_ega.patch"
           
SRC_URI[md5sum] = "b159fd2cfbd6c77a18be5e8fe067dc78"
SRC_URI[sha256sum] = "a899956b3ee46492696114d220431405320c64c1f6f058fdfc2b4d6a2beae786"

S = "${WORKDIR}/grx249"

INSANE_SKIP_${PN} += "already-stripped"

CFLAGS+=" -g --sysroot=${STAGING_DIR_TARGET}"

EXTRA_OEMAKE = "-f makefile.lnx 'CC=${CC}' 'LD=${LD}' 'RANLIB=${RANLIB}' 'AR=${AR}' \
        'CFLAGS=${CFLAGS} -I${S}/include'  'BUILDDIR=${S}' 'LDFLAGS=${LDFLAGS}'"

do_configure() {
	./configure --disable-svgalib --enable-direct-mouse --disable-x86_64 --target=console --enable-png --enable-jpeg
}


do_install() {
	INCLUDEDIR=${includedir}
	LIBDIR=${libdir}
	
	install -d ${D}${includedir}
	install -d ${D}${libdir}
	
	for f in ${S}/include/*.h; do
		install -m 0644 $f ${D}${includedir}
	done
	
	for f in ${S}/lib/unix/libgrx20.*; do
		install -m 0644 $f ${D}${libdir}
	done
}


PARALLEL_MAKE = ""

BBCLASSEXTEND = "native"
