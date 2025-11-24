#!/bin/bash
set -e

echo "Building wfantund..."
dpkg-buildpackage -us -uc

echo "Fetching TI SimpleLink SDK..."
if [ ! -d "linux-host/src/lprf-dallas-wisunfan/source" ]; then
    git clone --depth 1 https://github.com/TexasInstruments/simplelink-lowpower-f2-sdk.git linux-host/src/lprf-dallas-wisunfan
fi

echo "Building wisun-rcp-host..."
cd linux-host
dpkg-buildpackage -us -uc
cd ..

echo "Building ti-wisun-webapp..."
cd ti-wisun-webapp
dpkg-buildpackage -us -uc
cd ..

echo "Building unified metapackage..."
cd debian-unified
dpkg-buildpackage -us -uc
cd ..

echo "All packages built successfully!"
ls -l *.deb
