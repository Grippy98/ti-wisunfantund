SUMMARY = "TI Wi-SUN FAN RCP Host Application"
DESCRIPTION = "Linux Host application for TI Wi-SUN FAN Border Router, designed to connect to a TI device running the RCP LMAC Firmware."
HOMEPAGE = "https://github.com/TexasInstruments/ti-wisunfantund"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit cmake

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/TexasInstruments/ti-wisunfantund.git;protocol=https;branch=main \
           git://github.com/TexasInstruments/simplelink-lowpower-f2-sdk.git;protocol=https;branch=main;name=sdk;destsuffix=git/linux-host/src/lprf-dallas-wisunfan"

SRCREV_sdk = "${AUTOREV}"

S = "${WORKDIR}/git/linux-host"

EXTRA_OECMAKE = "-G Ninja"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/bin/wisun-rcp-host ${D}${bindir}/wisun-rcp-host
    
    install -d ${D}${sysconfdir}
    install -m 0644 ${S}/apps/border_router_nanostack_tirf/border_router_host.cfg ${D}${sysconfdir}/wisun-rcp-host.cfg
}
