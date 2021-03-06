/**
 * Copyright (c) 2010-2021 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.lutron.internal.radiora.protocol;

/**
 * Feedback for when a device was changed locally (not through Master Control)
 * <p>
 * <b>Syntax:</b>
 *
 * <pre>
 * {@code
 * LZC,<Zone Number>,<State>
 * }
 * </pre>
 *
 * <b>Examples:</b>
 * <p>
 * Dimmer 1 changed from 100% to 50%
 *
 * <pre>
 * LZC,01,CHG
 * </pre>
 *
 * Dimmer 4 changed from OFF to 25%
 *
 * <pre>
 * LZC,04,ON
 * </pre>
 *
 * @author Jeff Lauterbach - Initial Contribution
 *
 */
public class LocalZoneChangeFeedback extends RadioRAFeedback {

    private int zoneNumber; // 1 to 32
    private State state; // ON, OFF, CHG

    public enum State {
        ON,
        OFF,
        CHG
    }

    public LocalZoneChangeFeedback(String msg) {
        String[] params = parse(msg, 2);

        zoneNumber = Integer.parseInt(params[1].trim());
        state = State.valueOf(params[2].trim().toUpperCase());
    }

    public State getState() {
        return state;
    }

    public int getZoneNumber() {
        return zoneNumber;
    }
}
