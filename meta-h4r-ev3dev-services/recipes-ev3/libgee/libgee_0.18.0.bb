require libgee.inc
PE = "1"
PR = "${INC_PR}.2"
DEPENDS += "gobject-introspection-stub"
DEPENDS_virtclass-native += "gobject-introspection-stub-native"

SHRT_VER = "${@d.getVar('PV',1).split('.')[0]}.${@d.getVar('PV',1).split('.')[1]}"
SRC_URI = "http://ftp.gnome.org/pub/GNOME/sources/libgee/${SHRT_VER}/${BP}.tar.xz\
           file://introspection.patch"
SRC_URI[md5sum] = "29ea6125e653d7e60b49a9a9544abc96"
SRC_URI[sha256sum] = "4ad99ef937d071b4883c061df40bfe233f7649d50c354cf81235f180b4244399"
