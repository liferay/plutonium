/*
 * SPDX-FileCopyrightText: (c) 2003-2025 The Apache Software Foundation (ASF) and contributors.
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc.
 * SPDX-License-Identifier: Apache-2.0
 */
toggleNavigation = function() {
    if (document.getElementById) {
        navigationRoot = document.getElementById("navigation");
        if (navigationRoot) {
            navigationList = navigationRoot.getElementsByTagName("ul")[0];
            if (navigationList) {
                navigationList.style.visibility = "hidden";
                navigationRoot.onmouseover = function() {
                    navigationList.style.visibility = "visible";
                }
                navigationRoot.onmouseout = function() {
                    navigationList.style.visibility = "hidden";
                }
            }
        }
    }
}

window.onload = toggleNavigation;



