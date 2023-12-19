#!/bin/bash

# Color configuration (DO NOT CHANGE)
COLOR_RED="\033[0;31m"
COLOR_GREEN="\033[0;32m"
COLOR_BLUE="\033[0;34m"
COLOR_YELLOW="\033[0;33m"
COLOR_RESET="\033[0m"
TOMCAT_DIR="/opt/homebrew/Cellar/tomcat/10.1.15/libexec"

# array message
messages=(
  "📖😇: For I know the plans I have for you, declares the Lord, plans for welfare and not for evil, to give you a future and a hope. - Jeremiah 29:11"
  "📖😇: And we know that for those who love God all things work together for good, for those who are called according to his purpose. - Romans 8:28"
  "📖😇: Trust in the Lord with all your heart, and do not lean on your own understanding. - Proverbs 3:5"
  "📖😇: And whatever you do, in word or deed, do everything in the name of the Lord Jesus, giving thanks to God the Father through him. - Colossians 3:17"
  "📖😇: I can do all things through him who strengthens me. - Philippians 4:13"
  "📖😇: But seek first the kingdom of God and his righteousness, and all these things will be added to you. - Matthew 6:33"
  "📖😇: And let us not grow weary of doing good, for in due season we will reap, if we do not give up. - Galatians 6:9"
  "📖😇: Be strong and courageous. Do not fear or be in dread of them, for it is the Lord your God who goes with you. He will not leave you or forsake you. - Deuteronomy 31:6"
  "📖😇: I have said these things to you, that in me you may have peace. In the world you will have tribulation. But take heart; I have overcome the world. - John 16:33"
  "📖😇: For God gave us a spirit not of fear but of power and love and self-control. - 2 Timothy 1:7"
  "📖😇: But they who wait for the Lord shall renew their strength; they shall mount up with wings like eagles; they shall run and not be weary; they shall walk and not faint. - Isaiah 40:31"
  "📖😇: But seek first the kingdom of God and his righteousness, and all these things will be added to you. - Matthew 6:33"
)

echo -e "${COLOR_BLUE}🚀: Compiling... ${COLOR_RESET}"

# function compile
function compile {
  source_directory=$1
  out_directory=$2
  classpath=$3
  source_files=($(find "$source_directory" -type f -name "*.java"))
  reminding_files=${#source_files[@]}
  while [ ${#source_files[@]} -ne 0 ]; do
    for i in "${!source_files[@]}"; do
      javac -parameters -cp "$classpath:$out_directory" -d "$out_directory" "${source_files[i]}" 2>compilation.log
      compilation=$(<compilation.log)
      if [ ${#compilation[0]} -eq 0  ]; then
        echo -e "${COLOR_YELLOW}📖: Compiled ${source_files[i]} ${COLOR_RESET} to ${out_directory}"
        unset "source_files[$i]"
      fi
    done
    if [ ${#source_files[@]} -eq "$reminding_files" ]; then
      cat compilation.log
      rm -rf compilation.log
      echo -e "${COLOR_YELLOW}${messages[$RANDOM % ${#messages[@]}]}${COLOR_RESET}"
      echo -e "${COLOR_RED}😂: Compilation failed due to unresolved dependencies or mistakes in the code ${COLOR_RESET}"
      exit 1
    fi
    reminding_files=${#source_files[@]}
  done
  rm -rf compilation.log
}

# create out directory
mkdir -p out

# copy webapp resources
echo -e "${COLOR_BLUE}🚀: Copying webapp resources... ${COLOR_RESET}"
cp -r src/main/webapp/* out

# remove *.java inside out directory
find out -type f -name "*.java" -delete

# compile java files
compile . out/WEB-INF/classes lib/*:$TOMCAT_DIR/lib/*

# copy lib/*.jar to out/WEB-INF/lib
echo -e "${COLOR_BLUE}🚀: Copying lib/*.jar to out/WEB-INF/lib... ${COLOR_RESET}"
cp -R lib/ out/WEB-INF/lib/

# create war file
echo -e "${COLOR_BLUE}🚀: Creating war file... ${COLOR_RESET}"
jar -cvf out.war -C out .

# remove out directory
rm -rf out

# right click on war and run on the server
echo -e "${COLOR_BLUE}🚀: Right click on out.war and run on the server ${COLOR_RESET}"

echo -e "${COLOR_GREEN}😎: Build successful ${COLOR_RESET}"
