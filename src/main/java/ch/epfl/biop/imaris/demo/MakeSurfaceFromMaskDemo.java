package ch.epfl.biop.imaris.demo;

import Imaris.Error;
import Imaris.ISurfacesPrx;
import ch.epfl.biop.imaris.EasyXT;
import ij.IJ;
import ij.ImagePlus;
import ij.Prefs;
import ij.gui.WaitForUserDialog;

/**
 * EasyXT Demo
 * How to get a surface in ImageJ and send a modified version back to Imaris
 *
 * @author Romain Guiet
 * @author Nicolas Chiaruttini
 * @author Olivier Burri
 * January 2021
 * EPFL - SV - PTECH - PTBIOP
 */

public class MakeSurfaceFromMaskDemo {

    public static void main(String... args) throws Exception {

        try {
            // Surface created and shown in ImageJ
            MakeAndGetSurfaceDemo.main();

            ImagePlus surfaceImp = IJ.getImage(); // Surface Image

            if ((args.length > 0) && (args[0].equals("Test Mode"))) {
                IJ.log("The surface will be skeletonized ...");
            } else {
                new WaitForUserDialog("The surface will be skeletonized ...").show();
            }
            // because of skeletonize
            Prefs.blackBackground = true;
            IJ.run(surfaceImp, "Skeletonize", "stack");

            if ((args.length > 0) && (args[0].equals("Test Mode"))) {
                IJ.log("And sent back to Imaris ...");
            } else {
                new WaitForUserDialog("And sent back to Imaris ...").show();
            }

            ISurfacesPrx surface = EasyXT.Surfaces.create(surfaceImp);

            EasyXT.Scene.addItem(surface);
            surfaceImp.changes = false;

        } catch (Error error) {
            error.printStackTrace();
        }

    }
}