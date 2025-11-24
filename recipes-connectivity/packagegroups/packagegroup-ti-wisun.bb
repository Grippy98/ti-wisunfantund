SUMMARY = "TI Wi-SUN FAN Complete Solution Packagegroup"
DESCRIPTION = "Packagegroup to install all TI Wi-SUN FAN components."

inherit packagegroup

RDEPENDS:${PN} = "\
    wfantund \
    wisun-rcp-host \
    ti-wisun-webapp \
"
