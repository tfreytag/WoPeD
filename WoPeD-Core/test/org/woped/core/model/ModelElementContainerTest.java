package org.woped.core.model;

import org.jgraph.graph.DefaultPort;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.woped.core.Constants;
import org.woped.core.model.petrinet.AbstractPetriNetElementModel;
import org.woped.core.model.petrinet.PlaceModel;
import org.woped.core.model.petrinet.TransitionModel;
import org.woped.core.utilities.ILogger;
import org.woped.core.utilities.LoggerManager;

import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ModelElementContainerTest {

    @Before
    public void setup(){
        LoggerManager.resetForTesting();
    }

    @After
    public void tearDown(){
        LoggerManager.resetForTesting();
    }

    @Test
    public void addReference_missingSource_logsWarning(){

        ModelElementContainer sut = new ModelElementContainer();

        ILogger logger = mock(ILogger.class);
        LoggerManager.register(logger, Constants.CORE_LOGGER);

        PlaceModel source = new PlaceModel(new CreationMap());
        source.setId("fakeSource");

        TransitionModel target = new TransitionModel(new CreationMap());
        sut.addElement(target);

        ArcModel arc = mock(ArcModel.class);
        when(arc.getSourceId()).thenReturn(source.getId());

        sut.addReference(arc);

        verify(logger).warn("Source (ID: fakeSource) doesn't exist");
    }

    @Test
    public void addReference_missingTarget_logsWarning(){

        ModelElementContainer sut = new ModelElementContainer();

        ILogger logger = mock(ILogger.class);
        LoggerManager.register(logger, Constants.CORE_LOGGER);

        PlaceModel source = new PlaceModel(new CreationMap());
        source.setId("fakeSource");
        sut.addElement(source);

        TransitionModel target = new TransitionModel(new CreationMap());
        target.setId("fakeTarget");

        ArcModel arc = mock(ArcModel.class);
        when(arc.getSourceId()).thenReturn(source.getId());
        when(arc.getTargetId()).thenReturn(target.getId());

        sut.addReference(arc);

        verify(logger).warn("Target (ID:fakeTarget) does not exist");
    }

    @Test
    public void addReference_insertValidArc_arcExists(){

        ModelElementContainer sut = new ModelElementContainer();

        ILogger logger = mock(ILogger.class);
        LoggerManager.register(logger, Constants.CORE_LOGGER);

        PlaceModel source = new PlaceModel(new CreationMap());
        source.setId("fakeSource");
        sut.addElement(source);

        TransitionModel target = new TransitionModel(new CreationMap());
        target.setId("fakeTarget");
        sut.addElement(target);

        ArcModel expected = mock(ArcModel.class);
        when(expected.getId()).thenReturn("arc1");
        when(expected.getSourceId()).thenReturn(source.getId());
        when(expected.getTargetId()).thenReturn(target.getId());

        sut.addReference(expected);
        ArcModel actual = sut.getArcById(expected.getId());

        assertEquals(expected, actual);
    }

    @Test
    public void addReference_insertValidArc_logsMessage(){

        ModelElementContainer sut = new ModelElementContainer();

        ILogger logger = mock(ILogger.class);
        LoggerManager.register(logger, Constants.CORE_LOGGER);

        PlaceModel source = new PlaceModel(new CreationMap());
        source.setId("fakeSource");
        sut.addElement(source);

        TransitionModel target = new TransitionModel(new CreationMap());
        target.setId("fakeTarget");
        sut.addElement(target);

        ArcModel arc = mock(ArcModel.class);
        when(arc.getId()).thenReturn("arc1");
        when(arc.getSourceId()).thenReturn(source.getId());
        when(arc.getTargetId()).thenReturn(target.getId());

        sut.addReference(arc);

        verify(logger).debug("Reference: " + arc.getId() + " (" + arc.getSourceId() + " -> " + arc.getTargetId() + ") added.");
    }

    @Test
    public void addReference_insertArcTwice_logsWarning(){

        ModelElementContainer sut = new ModelElementContainer();

        ILogger logger = mock(ILogger.class);
        LoggerManager.register(logger, Constants.CORE_LOGGER);

        PlaceModel source = new PlaceModel(new CreationMap());
        source.setId("fakeSource");
        sut.addElement(source);

        TransitionModel target = new TransitionModel(new CreationMap());
        target.setId("fakeTarget");
        sut.addElement(target);

        ArcModel arc = mock(ArcModel.class);
        when(arc.getId()).thenReturn("arc1");
        when(arc.getSourceId()).thenReturn(source.getId());
        when(arc.getTargetId()).thenReturn(target.getId());

        sut.addReference(arc);
        sut.addReference(arc);

        verify(logger).debug("Arc already exists!");
    }

    @Test
    public void hasReference_ReferenceExists_returnsTrue(){

        ModelElementContainer sut = new ModelElementContainer();

        ILogger logger = mock(ILogger.class);
        LoggerManager.register(logger, Constants.CORE_LOGGER);

        PlaceModel source = new PlaceModel(new CreationMap());
        source.setId("fakeSource");
        sut.addElement(source);

        TransitionModel target = new TransitionModel(new CreationMap());
        target.setId("fakeTarget");
        sut.addElement(target);

        ArcModel arc = mock(ArcModel.class);
        when(arc.getId()).thenReturn("arc1");
        when(arc.getSourceId()).thenReturn(source.getId());
        when(arc.getTargetId()).thenReturn(target.getId());

        sut.addReference(arc);
        assertTrue( sut.hasReference(source.getId(), target.getId()));
    }

    @Test
    public void hasReference_ReferenceNotExists_returnsFalse(){

        ModelElementContainer sut = new ModelElementContainer();

        ILogger logger = mock(ILogger.class);
        LoggerManager.register(logger, Constants.CORE_LOGGER);

        PlaceModel source = new PlaceModel(new CreationMap());
        source.setId("fakeSource");
        sut.addElement(source);

        TransitionModel target = new TransitionModel(new CreationMap());
        target.setId("fakeTarget");
        sut.addElement(target);

        ArcModel arc = mock(ArcModel.class);
        when(arc.getId()).thenReturn("arc1");
        when(arc.getSourceId()).thenReturn(source.getId());
        when(arc.getTargetId()).thenReturn(target.getId());

        assertFalse(sut.hasReference(source.getId(), target.getId()));
    }

    @Test
    public void hasReference_arcRemoved_returnsFalse(){

        ModelElementContainer sut = new ModelElementContainer();

        ILogger logger = mock(ILogger.class);
        LoggerManager.register(logger, Constants.CORE_LOGGER);

        PlaceModel source = new PlaceModel(new CreationMap());
        source.setId("fakeSource");
        sut.addElement(source);

        TransitionModel target = new TransitionModel(new CreationMap());
        target.setId("fakeTarget");
        sut.addElement(target);

        ArcModel arc = mock(ArcModel.class);
        when(arc.getId()).thenReturn("arc1");
        when(arc.getSourceId()).thenReturn(source.getId());
        when(arc.getTargetId()).thenReturn(target.getId());

        sut.addReference(arc);
        assertTrue(sut.hasReference(source.getId(), target.getId())); // reference must be present

        sut.removeArc(arc.getId());
        assertFalse(sut.hasReference(source.getId(), target.getId()));
    }

    @Test
    public void removeElement_HasReferences_ReferenceRemoved(){

        ModelElementContainer sut = new ModelElementContainer();

        ILogger logger = mock(ILogger.class);
        LoggerManager.register(logger, Constants.CORE_LOGGER);

        PlaceModel source = new PlaceModel(new CreationMap());
        source.setId("fakeSource");
        sut.addElement(source);

        TransitionModel target = new TransitionModel(new CreationMap());
        target.setId("fakeTarget");
        sut.addElement(target);

        ArcModel arc = mock(ArcModel.class);
        when(arc.getId()).thenReturn("arc1");
        when(arc.getSourceId()).thenReturn(source.getId());
        when(arc.getTargetId()).thenReturn(target.getId());

        sut.addReference(arc);
        sut.removeElement(target.getId());

        assertFalse(sut.hasReference(source.getId(), target.getId()));
    }

    @Test
    public void removeArc_arcNotExists_logsWarning(){
        ModelElementContainer sut = new ModelElementContainer();

        ILogger logger = mock(ILogger.class);
        LoggerManager.register(logger, Constants.CORE_LOGGER);

        ArcModel arc = mock(ArcModel.class);
        when(arc.getId()).thenReturn("arc1");

        sut.removeArc(arc.getId());

        verify(logger).warn("Arc with ID: " + arc.getId() + " does not exists");
    }

    @Test
    public void removeArc_arcExists_logsMessage(){

        ModelElementContainer sut = new ModelElementContainer();

        ILogger logger = mock(ILogger.class);
        LoggerManager.register(logger, Constants.CORE_LOGGER);

        PlaceModel source = new PlaceModel(new CreationMap());
        source.setId("fakeSource");
        sut.addElement(source);

        TransitionModel target = new TransitionModel(new CreationMap());
        target.setId("fakeTarget");
        sut.addElement(target);

        ArcModel arc = mock(ArcModel.class);
        when(arc.getId()).thenReturn("arc1");
        when(arc.getSourceId()).thenReturn(source.getId());
        when(arc.getTargetId()).thenReturn(target.getId());

        sut.addReference(arc);
        sut.removeArc(arc.getId());

        verify(logger).debug("Reference (ID:" + arc.getId() + ") deleted");
}

    @Test
    public void removeArc_validArc_arcRemovedFromArcMap(){

        ModelElementContainer sut = new ModelElementContainer();

        ILogger logger = mock(ILogger.class);
        LoggerManager.register(logger, Constants.CORE_LOGGER);

        PlaceModel source = new PlaceModel(new CreationMap());
        source.setId("fakeSource");
        sut.addElement(source);

        TransitionModel target = new TransitionModel(new CreationMap());
        target.setId("fakeTarget");
        sut.addElement(target);

        ArcModel arc = mock(ArcModel.class);
        when(arc.getId()).thenReturn("arc1");
        when(arc.getSourceId()).thenReturn(source.getId());
        when(arc.getTargetId()).thenReturn(target.getId());

        sut.addReference(arc);
        assertTrue(sut.getArcMap().containsKey(arc.getId()));

        sut.removeArc(arc.getId());
        assertFalse(sut.getArcMap().containsKey(arc.getId()));
    }

    @Test
    public void removeArc_validArc_arcRemovedFromSourceReferenceMap(){

        ModelElementContainer sut = new ModelElementContainer();

        ILogger logger = mock(ILogger.class);
        LoggerManager.register(logger, Constants.CORE_LOGGER);

        PlaceModel source = new PlaceModel(new CreationMap());
        source.setId("fakeSource");
        sut.addElement(source);

        TransitionModel target = new TransitionModel(new CreationMap());
        target.setId("fakeTarget");
        sut.addElement(target);

        ArcModel arc = mock(ArcModel.class);
        when(arc.getId()).thenReturn("arc1");
        when(arc.getSourceId()).thenReturn(source.getId());
        when(arc.getTargetId()).thenReturn(target.getId());

        sut.addReference(arc);
        assertTrue(sut.containsArc(arc));

        sut.removeArc(arc.getId());
        assertFalse(sut.containsArc(arc));
    }

    @Test
    public void getTargetElements_idNotExists_returnsNull(){
        ModelElementContainer sut = new ModelElementContainer();

        Map<String, AbstractPetriNetElementModel> actual = sut.getTargetElements("notExistingId");

        assertNull(actual);
    }

    @Test
    public void getTargetElements_has2Targets_returnsTargets(){
        ModelElementContainer sut = new ModelElementContainer();

        ILogger logger = mock(ILogger.class);
        LoggerManager.register(logger, Constants.CORE_LOGGER);

        PlaceModel source = new PlaceModel(new CreationMap());
        source.setId("fakeSource");
        sut.addElement(source);

        TransitionModel target1 = new TransitionModel(new CreationMap());
        target1.setId("fakeTarget1");
        sut.addElement(target1);

        TransitionModel target2 = new TransitionModel(new CreationMap());
        target2.setId("fakeTarget2");
        sut.addElement(target2);

        TransitionModel target3 = new TransitionModel(new CreationMap());
        target3.setId("fakeTarget3");
        sut.addElement(target3);

        DefaultPort port1 = mock(DefaultPort.class);
        when(port1.getParent()).thenReturn(target1);

        ArcModel arc1 = mock(ArcModel.class);
        when(arc1.getId()).thenReturn("arc1");
        when(arc1.getSourceId()).thenReturn(source.getId());
        when(arc1.getTargetId()).thenReturn(target1.getId());
        when(arc1.getTarget()).thenReturn(port1);

        DefaultPort port2 = mock(DefaultPort.class);
        when(port2.getParent()).thenReturn(target2);

        ArcModel arc2 = mock(ArcModel.class);
        when(arc2.getId()).thenReturn("arc2");
        when(arc2.getSourceId()).thenReturn(source.getId());
        when(arc2.getTargetId()).thenReturn(target2.getId());
        when(arc2.getTarget()).thenReturn(port2);

        sut.addReference(arc1);
        sut.addReference(arc2);

        Map<String, AbstractPetriNetElementModel> result = sut.getTargetElements(source.getId());

        assertTrue(result.containsKey(target1.getId()));
        assertEquals(result.get(target1.getId()), target1);

        assertTrue(result.containsKey(target2.getId()));
        assertEquals(result.get(target2.getId()), target2);

        assertFalse(result.containsKey(target3));
    }
}