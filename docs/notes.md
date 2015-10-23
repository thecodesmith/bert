# Miscellaneous Project Notes

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

Tutorial on setting up wireless access point:

http://www.daveconroy.com/turn-your-raspberry-pi-into-a-wifi-hotspot-with-edimax-nano-usb-ew-7811un-rtl8188cus-chipset/
