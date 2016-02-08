DESCRIPTION = "A image for the Lego ev3 brick"
include recipes-core/images/core-image-minimal.bb
inherit extrausers


DISTRO_NAME = "H4R-EV3 Yocto (http://github.com/Hacks4ROS)"
MACHINE = "ev3dev"
IMAGE_FEATURES += " package-management"
EXTRA_IMAGE_FEATURES += " empty-root-password"
PACKAGE_CLASSES ?= "package_deb"
                
KERNEL_MODULES += " \ 
    kernel-modules \
"

SYSTEM_ADDONS = " \
	connman \
"

ROS =" \
    packagegroup-ros-comm \
    actionlib \
    actionlib-msgs \
    diagnostic-msgs \
    geometry-msgs \
    nav-msgs \
    sensor-msgs \
    shape-msgs \
    stereo-msgs \
    trajectory-msgs \
    visualization-msgs \
    dynamic-reconfigure \
    angles \
    tf2 \
    tf2-geometry-msgs \
    tf2-kdl \
    tf2-msgs \
    tf2-ros \
    tf2-tools \
    eigen-conversions \
    kdl-conversions \
    tf \
    tf-conversions \
    image-transport \
    nodelet-topic-tools \
    nodelet \
    cv-bridge \
    compressed-image-transport \
    compressed-depth-image-transport \
    theora-image-transport \
    cmake-modules \
    urdfdom \
    control-msgs \
    realtime-tools \
    control-toolbox \
    controller-interface \
    controller-manager-msgs \
    controller-manager-tests \
    controller-manager \
    hardware-interface \
    joint-limits-interface \
    transmission-interface \
    collada-parser \
    kdl-parser \
    resource-retriever \
    urdf-parser-plugin \
    urdf \
    effort-controllers \
    force-torque-sensor-controller \
    forward-command-controller \
    imu-sensor-controller \
    joint-state-controller \
    position-controllers \
    velocity-controllers \
    image-geometry \
    camera-calibration-parsers \
    camera-info-manager \
    polled-camera \
    filters \
    smach \
    smach-msgs \
    smach-ros \
    eigen-stl-containers \
    image-proc \
    image-rotate \
    image-view \
    stereo-image-proc \
    shape-tools \
    laser-geometry \
    map-msgs \
    diagnostic-aggregator \
    diagnostic-analysis \
    diagnostic-common-diagnostics \
    diagnostic-updater \
    robot-state-publisher \
    geometric-shapes \
    depth-image-proc \
    rgbd-launch \
    amcl \
    map-server \
    move-base-msgs \
    move-slow-and-clear \
    nav-core \
    navfn \
    rotate-recovery \
    voxel-grid \
    audio-common-msgs \
    camera-info-manager-py \
    diff-drive-controller \
    gripper-action-controller \
    gscam \
    joint-trajectory-controller \
    sound-play \
"

EV3 ="\
	libgee   \
	libgrx   \
	brickman \
	ev3systemd \
	ev3firmware \
	ev3dev-lang-cpp \
	ev3dev-lang-cpp-examples \
	h4r-ev3-control \
	h4r-ev3-manager \
	h4r-ev3-msgs \ 
	h4r-ev3-launch \
"	

REMOTE="\
	openssh  \
"

PYTHON_ROS =" \
    python \
    python-core \
    python-rosinstall \
    python-rosdep \
    python-wstool \
    python-distutils \
    python-rospkg \
    python-catkin-pkg \
    python-nose \
    python-pyyaml \
    python-vcstools \
    python-resource \
    python-docutils \
    python-pip \
    python-gst \
    python-dev \
    tbb \
    roscpp-traits \
    python-numpy \
    python-numeric \
    python-empy \
"

LIBRARY_PACKAGES =" \
    libc6-dev \
    libc-dev \
    libstdc++-dev \
    libbz2 \
    ldd \
    libtool \
"

WIFI_SUPPORT = " \
    wpa-supplicant \
    wireless-tools \
    iw \
    crda \
"

FIRMWARE_SUPPORT = " \
    linux-firmware \
"

MAKE_TOOLS = " \
    make \
    pkgconfig \
    python-json \
    json-glib \
    json-c \
    gnupg \
    gpgme \
    cmake \
    cmake-modules \
    binutils \
    coreutils \
"

UPDATE_EXTRA=" \
    git \
    ca-certificates \
    apt \
    curl \
    wget \
    tzdata \
    ntp \
"

DEV_EXTRAS = " \
    avahi-daemon \
"

CONSOLE_TOOLS = " \
    usbutils \
    less \
    diffutils \
    file \
"

EDITORS = " \
    vim \
"

#NOT INSTALLED
FORTRAN_TOOLS = " \
    gfortran \
    gfortran-symlinks \
    libgfortran \
    libgfortran-dev \
"


EXTRA_TOOLS_INSTALL = " \
    bzip2 \
    devmem2 \
    ethtool \
    findutils \
    iperf \
    htop \
    procps \
    sysfsutils \
    media-ctl \
    nodelet \
    class-loader \
    message-runtime \
    e2fsprogs \
"

#NOT INSTALLED
DEV_SDK_INSTALL = " \
    cpp \
    cpp-symlinks \
    gcc \
    gcc-symlinks \
    g++ \
    g++-symlinks \
    libstdc++ \
    libstdc++-dev \
"



IMAGE_INSTALL += " \
    ${SYSTEM_ADDONS} \
    ${EV3} \
    ${ROS} \
    ${UPDATE_EXTRA} \
    ${MAKE_TOOLS} \
    ${CONSOLE_TOOLS} \
    ${EXTRA_TOOLS_INSTALL} \
    ${WIFI_SUPPORT} \
    ${PYTHON_ROS} \
    ${KERNEL_MODULES} \
    ${LIBRARY_PACKAGES} \
    ${EDITORS} \
    ${REMOTE} \
    vsftpd \
    inetutils \
    bash \
"

set_env_path_files() {
    touch ${IMAGE_ROOTFS}/opt/ros/indigo/.catkin
    mkdir -p ${IMAGE_ROOTFS}/catkin_ws/src

    touch ${IMAGE_ROOTFS}/etc/environment
    echo '
ROS_ROOT=/opt/ros/indigo
PATH=$PATH:/opt/ros/indigo/bin
LD_LIBRARY_PATH=/opt/ros/indigo/lib
PYTHONPATH=/opt/ros/indigo/lib/python2.7/site-packages
ROS_MASTER_URI=http://localhost:11311
CMAKE_PREFIX_PATH=/opt/ros/indigo
' >> ${IMAGE_ROOTFS}/etc/environment
 	
 	touch ${IMAGE_ROOTFS}/opt/ros/indigo/.catkin
 	
 	echo '
export ROS_ROOT=/opt/ros/indigo
export PATH=$PATH:/opt/ros/indigo/bin
export LD_LIBRARY_PATH=/opt/ros/indigo/lib
export PYTHONPATH=/opt/ros/indigo/lib/python2.7/site-packages
export ROS_MASTER_URI=http://localhost:11311
export CMAKE_PREFIX_PATH=/opt/ros/indigo
touch /opt/ros/indigo/.catkin' 
	>> ${IMAGE_ROOTFS}/home/root/.profile
	
 	#ln -sf ${IMAGE_ROOTFS}/etc/environment ${IMAGE_ROOTFS}/home/root/.profile
}

set_sources(){
    touch ${IMAGE_ROOTFS}/etc/apt/sources.list.d/mysources.list
    echo 'deb http://ftp.debian.org/debian/ wheezy main contrib non-free\n
    deb-src http://ftp.debian.org/debian/ wheezy main contrib non-free\n' >> ${IMAGE_ROOTFS}/etc/apt/sources.list.d/mysources.list
}


update_bootmodules() {
    modules_list="
    asix\n
    rfkill\n
    "
    
    echo $modules_list >> ${IMAGE_ROOTFS}/etc/modules
}

set_host() {
    echo '127.0.0.1       ev3dev.localdomain	localhost     ev3dev' > ${IMAGE_ROOTFS}/etc/hosts
    echo ev3dev > ${IMAGE_ROOTFS}/etc/hostname
}

set_network() {

    network_conf="
    ctrl_interface=/var/run/wpa-supplicant\n
    ctrl_interface_group=0\n
    update_config=1\n
    \n
    network={\n
	ssid='Robotica'\n
	psk=543eccddeed68ac717b419559cb17d04bc9af25ecef1ad6cec71e9335bc612cf\n
	}\n"
	
    echo $network_conf > ${IMAGE_ROOTFS}/etc/wpa_supplicant.conf
    chmod +rw ${IMAGE_ROOTFS}/etc/wpa_supplicant.conf
    
	#TODO REMOVE:
    interfaces_conf="
    auto lo\n
    iface lo inet loopback\n\n

    allow-hotplug wlan0\n
    auto wlan0\n\n

    iface wlan0 inet dhcp\n
    \t wireless_mode managed\n
    \t wireless_essid any\n
    \t wpa-driver wext\n
    \t wpa-conf /etc/wpa-supplicant.conf"

    #echo $interfaces_conf >> ${IMAGE_ROOTFS}/etc/network/interfaces
}

set_utc_timezone() {
    #Set locale time to Coordinated Universal Time
    ln -sf ${IMAGE_ROOTFS}/usr/share/zoneinfo/Etc/UTC ${IMAGE_ROOTFS}/etc/localtime
    #DATE=$(date +'%d %B %Y %H:%M:%S')
    #date -s $DATE
}

disable_bootlogd() {
    echo BOOTLOGD_ENABLE=no > ${IMAGE_ROOTFS}/etc/default/bootlogd
}

ROOTFS_POSTPROCESS_COMMAND_append = " \
    set_utc_timezone ; \
    set_env_path_files ; \
    set_host ; \
    set_network; \
    update_bootmodules; \
"

# The optimal size would be (total size - IMAGE_ROOTFS_ALIGNMENT - BOOT_SPACE)/1024  = (3909091328- 20971520)/1024 = 3796992
# 3796992


#IMAGE_EXTRA_SPACE = "2000000"
#IMAGE_ROOTFS_SIZE = "3489792"

#fork/exec system calls are very expensive.  Because of this, calls to executables from shells are slow. I Even an echo in a BusyBox shell results in a fork syscall! 
#I Select Shells -> Standalone shell in BusyBox configuration to make the shell call applets whenever possible

#It is probably a good idea to increase the log buffer size with CONFIG_LOG_BUF_SHIFT in your kernel configuration.  You will also need CONFIG_PRINTK_TIME and CONFIG_KALLSYMS
#CONFIG_PRINTK_TIME=y
#CONFIG_LOG_BUF_SHIFT=21
#CONFIG_KALLSYMS=y
#CONFIG_DETECT_HUNG_TASK=y

#need to remove display because we have serial
#MACHINE_FEATURES += "usbhost keyboard vfat ext2 wifi sdio"
