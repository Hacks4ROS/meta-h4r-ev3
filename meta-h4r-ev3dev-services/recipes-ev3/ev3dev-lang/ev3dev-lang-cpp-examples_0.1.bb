require ev3dev-lang.inc

DEPENDS="ev3dev-lang-cpp"

SECTION = "examples" 
S = "${WORKDIR}/git"

CCFLAGS+=" -std=c++11 -D_GLIBCXX_USE_NANOSLEEP"
CFLAGS+=" -std=c99 -I${S}"
LDFLAGS+=" -lstdc++ -lm -lev3dev -lpthread"

do_compile(){
	cd cpp
	mkdir -p obj
	mkdir -p lib
	cd obj
	${CC}  ../button-test.cpp -o ev3_button-test ${LDFLAGS} ${CCFLAGS} ${CFLAGS}
	${CC} ../drive-test.cpp -o ev3_drive-test ${LDFLAGS} ${CCFLAGS} ${CFLAGS}
	${CC} ../ev3dev-lang-demo.cpp -o ev3_ev3dev-lang-demo ${LDFLAGS} ${CCFLAGS} ${CFLAGS} 
	${CC} ../ev3dev-lang-test.cpp -o ev3_ev3dev-lang-test ${LDFLAGS} ${CCFLAGS} ${CFLAGS}
	${CC} ../remote_control-test.cpp -o ev3_remote_control-test ${LDFLAGS} ${CCFLAGS} ${CFLAGS}
}        

do_install(){
	cd cpp/obj
	install -d ${D}/usr/local/bin
	install -m 0755 ev3_button-test 		${D}/usr/local/bin/
	install -m 0755 ev3_drive-test 			${D}/usr/local/bin/
	install -m 0755 ev3_ev3dev-lang-demo 	${D}/usr/local/bin/
	install -m 0755 ev3_ev3dev-lang-test 	${D}/usr/local/bin/
	install -m 0755 ev3_remote_control-test ${D}/usr/local/bin/
}


FILES_${PN} += "\
/usr/local/bin/*\
"

FILES_${PN}-dbg += "\
/usr/local/bin/.debug/*\
"