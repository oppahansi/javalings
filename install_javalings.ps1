#!/usr/bin/env pwsh

Write-Host "Let's get you set up with Javaligns!" -ForegroundColor DarkCyan;
Write-Host "`n"

Write-Host "Checking requirements..." -ForegroundColor DarkCyan;
if (Get-Command choco -ErrorAction SilentlyContinue)
{
    Write-Host "SUCCESS: Chocolatey is installed" -ForegroundColor DarkCyan;
}
else
{
    Write-Host "WARNING: Chocolatey does not seem to be installed." -ForegroundColor DarkCyan;
    Write-Host "Installing Chocolatey package manager.." -ForegroundColor DarkCyan;
    ` Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
}
Write-Host "`n"


Write-Host "Refreshing environment varibales..." -ForegroundColor DarkCyan;
` refreshenv
Write-Host "`n"

Write-Host " Enabling color codes in consoles..." -ForegroundColor DarkCyan;
` Set-ItemProperty HKCU:\Console VirtualTerminalLevel -Type DWORD 1

Write-Host "Enabling auto confirmations for pre requisites installations.." -ForegroundColor DarkCyan;
` choco feature enable -n=allowGlobalConfirmation
Write-Host "`n"


if (Get-Command java -ErrorAction SilentlyContinue)
{
    Write-Host "SUCCESS: Java is installed" -ForegroundColor DarkCyan;
    Write-Host "Checking Java version ..." -ForegroundColor DarkCyan;

    $javaVersion = (Get-Command java | Select-Object -ExpandProperty Version).toString();

    if ($javaVersion.Contains("17") -Or $javaVersion.Contains("18") -Or $javaVersion.Contains("19") -Or $javaVersion.Contains("20") -Or $javaVersion.Contains("20"))
    {
        Write-Host "SUCCESS: Java $javaVersion is installed" -ForegroundColor DarkCyan;
    }
    else
    {
        Write-Host "ERROR: Recommended java version is 17+" -ForegroundColor Red;
        Write-Host "ERROR: Javalings needs a higher java version." -ForegroundColor Red;
        exit 1
    }
}
else
{
    Write-Host "WARNING: Java does not seem to be installed." -ForegroundColor DarkCyan;
    Write-Host "Installing OpenJDK 17..." -ForegroundColor DarkCyan;
    ` choco install openjdk --version=17.0.2
}
Write-Host "`n"

if (Get-Command git -ErrorAction SilentlyContinue)
{
    Write-Host "SUCCESS: Git is installed" -ForegroundColor DarkCyan;
}
else
{
    Write-Host "WARNING: Git does not seem to be installed." -ForegroundColor DarkCyan;
    Write-Host "Installing Git..." -ForegroundColor DarkCyan;
    ` choco install git
}
Write-Host "`n"

Write-Host "Refreshing environment varibales..." -ForegroundColor DarkCyan;
refreshenv
Write-Host "`n"

Write-Host "Disabling auto confirmations installations.." -ForegroundColor DarkCyan;
` choco feature disable -n=allowGlobalConfirmation
Write-Host "`n"

Write-Host "Cloning javalings repository..." -ForegroundColor DarkCyan;
` git clone https://github.com/oppahansi/javalings.git
Write-Host "`n"

` cd javalings/

Write-Host "All done! Run '.\javaligns.bat watch' to get started." -ForegroundColor DarkCyan;
Write-Host "`n"