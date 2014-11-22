package org.woped.qualanalysis.soundness.algorithms.basedonmarkingnet.unboundplaces;

import java.util.HashSet;
import java.util.Set;

import org.woped.qualanalysis.soundness.algorithms.basedonmarkingnet.AbstractMarkingNetTest;
import org.woped.qualanalysis.soundness.datamodel.PlaceNode;
import org.woped.qualanalysis.soundness.marking.IMarkingNet;
import org.woped.qualanalysis.soundness.marking.Marking;

/**
 * indicates all unbounded places.
 * 
 * @see IUnboundedPlacesTest
 * @author Patrick Spies, Patrick Kirchgaessner, Joern Liebau, Enrico Moeller, Sebastian Fuss
 * 
 */
public class UnboundPlacesTest extends AbstractMarkingNetTest implements IUnboundedPlacesTest {

	/**
	 * 
	 * @param iMarkingNet MarkingNet the algorithm is based on
	 */
    public UnboundPlacesTest(IMarkingNet markingNet) {
        super(markingNet);
    }

    /**
     * indicates all unbounded places. costs: count of markings * count of places
     * @see IUnboundedPlacesTest#getUnboundedPlaces()
     */
    @Override
    public Set<PlaceNode> getUnboundedPlaces() {
        Set<PlaceNode> unboundedPlaces = new HashSet<PlaceNode>();
        boolean[] unboundedPlacesArr;

        for (Marking marking : mNet.getMarkings()) {
            unboundedPlacesArr = marking.getPlaceUnlimited();

            for (int i = 0; i < unboundedPlacesArr.length; i++) {
                if (unboundedPlacesArr[i]) {
                    unboundedPlaces.add(mNet.getPlaces()[i]);
                }
            }
        }
        return unboundedPlaces;
    }

}
