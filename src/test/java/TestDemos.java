/*-
 * #%L
 * API and commands to facilitate communication between Imaris and FIJI
 * %%
 * Copyright (C) 2020 - 2021 ECOLE POLYTECHNIQUE FEDERALE DE LAUSANNE, Switzerland, BioImaging And Optics Platform (BIOP)
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */
import ch.epfl.biop.imaris.EasyXT;
import ch.epfl.biop.imaris.demo.*;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

/**
 * Execute each demo one at a time - no error should be thrown
 */

public class TestDemos {
    private static String mode = "Test Mode";

    final public static String OS_OK_FOR_TEST = "Windows 10";

    public static String getOperatingSystem() {
        String os = System.getProperty("os.name");
        return os;
    }

    static public boolean envOkForTest() {
        if (!getOperatingSystem().equals(OS_OK_FOR_TEST)) return false;
        return new File("C:/Program Files/Bitplane").exists();
    }

    @Test
    public void AddChildObjects() throws Exception {
        if (envOkForTest())
        AddChildObjects.main(mode);
    }

    @Test
    public void AddStatsDemo() throws Exception {
        if (envOkForTest())
        AddStatsDemo.main(mode);
    }

    @Test
    public void AllGetters() throws Exception {
        if (envOkForTest())
        AllGettersDemo.main(mode);
    }

    @Test
    public void FreshStartWithIJAndBIOPImsSample() throws Exception {
        if (envOkForTest())
        FreshStartWithIJAndBIOPImsSample.main(mode);
    }

    @Test
    public void GetStatisticsDemo() throws Exception {
        if (envOkForTest())
        GetStatisticsDemo.main(mode);
    }

    @Test
    public void LaunchIJFromEasyXT() throws Exception {
        if (envOkForTest())
        EasyXT.main(mode);
    }

    @Test
    public void MakeAndGetSpotsDemo() throws Exception {
        if (envOkForTest())
        MakeAndGetSpotsDemo.main(mode);
    }

    @Test
    public void MakeAndGetSurfaceDemo() throws Exception {
        if (envOkForTest())
        MakeAndGetSurfaceDemo.main(mode);
    }

    @Test
    public void MakeSurfaceFromMaskDemo() throws Exception {
        if (envOkForTest())
        MakeSurfaceFromMaskDemo.main(mode);
    }

    @Test
    public void TrackSurfaceDemo() throws Exception {
        if (envOkForTest())
        MakeSurfaceFromMaskDemo.main(mode);
    }

}
