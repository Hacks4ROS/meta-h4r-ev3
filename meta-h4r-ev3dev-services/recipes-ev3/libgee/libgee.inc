DESCRIPTION = "libgee is a collection library providing GObject-based interfaces \
and classes for commonly used data structures."
HOMEPAGE = "http://live.gnome.org/Libgee"
SECTION = "libs"
DEPENDS = "glib-2.0"
BBCLASSEXTEND = "native"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"
INC_PR = "r6"
PE = "1"

EXTRA_OECMAKE=""
EXTRA_OECONF="--disable-introspection"
EXTRA_OEMAKE=""
EXTRA_OESCONS=""

inherit autotools vala pkgconfig

   