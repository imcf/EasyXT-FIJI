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
package ch.epfl.biop.imaris.demo;

import Imaris.Error;
import Imaris.IDataSetPrx;
import ch.epfl.biop.imaris.EasyXT;
import ch.epfl.biop.imaris.ImarisCalibration;
import ij.IJ;
import ij.ImagePlus;
import ij.gui.WaitForUserDialog;

/**
 * EasyXT Demo
 * <p>
 * Show how to add a channel to the imaris dataset
 * <p>
 * Two ways are provided, a fast and a slow one.
 *
 * @author Olivier Burri
 * @author Nicolas Chiaruttini
 * <p>
 * October 2020
 * <p>
 * EPFL - SV -PTECH - PTBIOP
 */

public class AddChannelsToDataset {

    public static void main(String... args) throws Exception {
        try {
            // Fresh Start with the BIOP sample dataset
            FreshStartWithIJAndBIOPImsSample.main();

            //Get Extents of currently open dataset to create the same thing, but with two channels
            IDataSetPrx dataset = EasyXT.Dataset.getCurrent();

            ImarisCalibration cal = new ImarisCalibration(dataset);
            int bitDepth = EasyXT.Dataset.getBitDepth(dataset);

            new WaitForUserDialog("Creating a new dataset, adding channels and sending the dataset back").show();
            long t0 = System.currentTimeMillis();

            ImagePlus channelsImp1 = IJ.createImage("HyperStack One", bitDepth + "-bit color-mode label", cal.xSize, cal.ySize, 2, cal.zSize, cal.tSize);
            channelsImp1.show();

            // Copy the dataset
            IDataSetPrx newDataset = dataset.Clone();
            EasyXT.Dataset.addChannels(channelsImp1, newDataset);

            // Place the dataset into the scene
            EasyXT.Dataset.setCurrent(newDataset);

            // Close the dataset
            dataset.Dispose();

            long t1 = System.currentTimeMillis() - t0;
            IJ.log("New dataset time: " + t1 + " ms");

            new WaitForUserDialog("We will now add channels to the current dataset").show();

            long t2 = System.currentTimeMillis();
            ImagePlus channelsImp2 = IJ.createImage("HyperStack Two", bitDepth + "-bit color-mode label", cal.xSize, cal.ySize, 2, cal.zSize, cal.tSize);
            channelsImp2.show();
            EasyXT.Dataset.addChannels(channelsImp2);
            long t3 = System.currentTimeMillis() - t2;
            IJ.log("Append to current  dataset time: " + t3 + " ms");

        } catch (Error error) {
            System.out.println("ERROR:" + error.mDescription);
            System.out.println("LOCATION:" + error.mLocation);
            System.out.println("String:" + error.toString());
        }
    }
}
