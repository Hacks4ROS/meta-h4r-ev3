require ev3dev-lang.inc

SECTION = "libs" 
S = "${WORKDIR}/git"

CCFLAGS+=" -std=c++11 -D_GLIBCXX_USE_NANOSLEEP  -I${S}"

do_compile(){
	cd cpp
	mkdir -p obj
	mkdir -p lib
	${CC} -fPIC -g -c ev3dev.cpp ${CCFLAGS} ${CFLAGS} -o obj/ev3dev.o
	${CC} -shared -Wl,-soname,libev3dev.so.1 -o lib/libev3dev.so.1.0 obj/ev3dev.o	 
	${AR} -cvq lib/libev3dev.a obj/ev3dev.o -o lib/libev3dev.a
}        
        
do_install() {
	cd cpp
	install -d ${D}${includedir}
	install -d ${D}${libdir}

	install -m 0755 lib/libev3dev.a ${D}${libdir}
	install -m 0644 ev3dev.h ${D}${includedir}/ev3dev.h
	install -m 0755 lib/libev3dev.so.1.0 ${D}${libdir}
	ln -s libev3dev.so.1.0 ${D}/${libdir}/libev3dev.so.1
	ln -s libev3dev.so.1   ${D}/${libdir}/libev3dev.so
}
        
FILES_${PN}-dev += "\
${includedir}/ev3dev.h\
${libdir}/libev3dev.a\
"