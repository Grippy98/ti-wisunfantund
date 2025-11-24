SUMMARY = "Userspace Wireless Field Area Network (WFAN) Network Daemon"
DESCRIPTION = "wfantund is a user-space network interface driver/daemon that provides a native IPv6 network interface to TI Wi-SUN FAN Border Router operating in Network Processor (NWP) mode."
HOMEPAGE = "https://github.com/TexasInstruments/ti-wisunfantund"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit autotools pkgconfig systemd

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/TexasInstruments/ti-wisunfantund.git;protocol=https;branch=main"

S = "${WORKDIR}/git"

DEPENDS = "dbus readline boost libcoap"
RDEPENDS:${PN} = "dbus libreadline libcoap"

EXTRA_OECONF = "--sysconfdir=/etc --prefix=/usr"

SYSTEMD_SERVICE:${PN} = "wfantund.service"

do_configure:prepend() {
    (cd ${S}; ./bootstrap.sh)
}
