/*
 *      Copyright (C) 2012, 2016  higherfrequencytrading.com
 *      Copyright (C) 2016 Roman Leventov
 *
 *      This program is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU Lesser General Public License as published by
 *      the Free Software Foundation, either version 3 of the License.
 *
 *      This program is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU Lesser General Public License for more details.
 *
 *      You should have received a copy of the GNU Lesser General Public License
 *      along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.openhft.chronicle.hash.impl.stage.hash;

import net.openhft.chronicle.hash.impl.VanillaChronicleHashHolder;
import net.openhft.sg.StageRef;
import net.openhft.sg.Staged;

import java.util.ConcurrentModificationException;

@Staged
public class OwnerThreadHolder {

    @StageRef VanillaChronicleHashHolder<?> hh;

    final Thread owner = Thread.currentThread();

    public void checkAccessingFromOwnerThread() {
        if (owner != Thread.currentThread()) {
            throw new ConcurrentModificationException(hh.h().toIdentityString() +
                    ": Context shouldn't be accessed from multiple threads");
        }
    }
}
