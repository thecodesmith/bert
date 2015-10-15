# Miscellaneous Project Notes

## Item List

Raspberry Pi 2 Model B
Edimax EW-7811Un 150Mbps 11n Wi-Fi USB Adapter
Anker 2nd Generation Astro Mini 3350mAh Portable Charger
Kingston Digital 16 GB microSDHC Flash Card with SD Adapter
2x Motors
Motor controller board
Chassis
4x AA rechargeable batteries

## Setup Notes

Default login: pi/raspberry

## Disk Imaging

Find which device the SD card is:

    diskutil list

Unmount the device (example using /dev/disk2):

    diskutil unmountDisk /dev/disk2

Backup:

    dd if=/dev/disk2 of=pi.img bs=1m

Restore:

    dd if=pi.img of=/dev/disk2 bs=1m

## Wireless Setup

Tutorial on setting up WiFi using the Edimax adapter:
http://www.savagehomeautomation.com/projects/raspberry-pi-installing-the-edimax-ew-7811un-usb-wifi-adapte.html
