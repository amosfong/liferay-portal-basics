#!/bin/bash

CURRENT_DIR_NAME=$(dirname ${BASH_SOURCE[0]})

echo CURRENT_DIR_NAME=${CURRENT_DIR_NAME}

source ${CURRENT_DIR_NAME}/../../../../env/common.sh

function main {
	stop_client_extension_spring_boot_application workspaces/liferay-jethr0-workspace/client-extensions/liferay-jethr0-etc-spring-boot

	default_tear_down
}

main "${@}"