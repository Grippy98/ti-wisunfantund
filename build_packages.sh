#!/bin/bash
set -e

echo "Building wfantund..."
dpkg-buildpackage -us -uc

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
