SUMMARY = "TI Wi-SUN FAN Web Application"
DESCRIPTION = "Web application for configuring and monitoring TI Wi-SUN FAN Border Router."
HOMEPAGE = "https://github.com/TexasInstruments/ti-wisunfantund"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

inherit npm systemd

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/TexasInstruments/ti-wisunfantund.git;protocol=https;branch=main"

S = "${WORKDIR}/git/ti-wisun-webapp"

# Node.js dependency
DEPENDS = "nodejs-native"
RDEPENDS:${PN} = "nodejs bash"

SYSTEMD_SERVICE:${PN} = "ti-wisun-webapp.service"

do_compile() {
    # Build Client
    cd ${S}/client
    npm install
    npm run build

    # Install Server dependencies
    cd ${S}/server
    npm install --production
}

do_install() {
    install -d ${D}${datadir}/ti-wisun-webapp
    cp -r ${S}/client ${D}${datadir}/ti-wisun-webapp/
    cp -r ${S}/server ${D}${datadir}/ti-wisun-webapp/
    cp -r ${S}/resources ${D}${datadir}/ti-wisun-webapp/

    # Install Systemd Service
    install -d ${D}${systemd_unitdir}/system
    cat > ${D}${systemd_unitdir}/system/ti-wisun-webapp.service <<EOF
[Unit]
Description=TI Wi-SUN FAN Web Application
After=network.target wfantund.service

[Service]
Type=simple
ExecStart=/usr/bin/node /usr/share/ti-wisun-webapp/server/src/index.js
Restart=always
User=root
Environment=NODE_ENV=production

[Install]
WantedBy=multi-user.target
EOF
}

FILES:${PN} += "${datadir}/ti-wisun-webapp"
