driver = new HardwareDriver()
println driver.getMaxSpeed()
println driver.maxSpeed
println driver.setSpeeds(300, -300)
Thread.sleep(500)
println driver.setSpeeds(-300, 300)
Thread.sleep(500)
println driver.setSpeeds(0, 0)
