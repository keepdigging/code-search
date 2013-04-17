package de.jdemo.runner.path.test;

import java.io.IOException;

import de.jdemo.runner.path.ISourcePathElement;
import de.jdemo.runner.path.ZipSourcePathElement;

/**
 * @author Markus Gebhard
 */
public class JarSourcePathElementTest extends TestDataTestCase {
  private static final String JAVA_CLASS_NAME1 = "de.TestDataClass4"; //$NON-NLS-1$
  private ISourcePathElement pathElement;

  protected void setUp() throws Exception {
    pathElement = new ZipSourcePathElement(JAR_FILE);
  }

  public void testTestPreconditions() {
    assertTrue(JAR_FILE.exists());
  }

  public void testToString() {
    assertEquals(JAR_FILE.getName(), pathElement.toString());
  }

  public void testExistingSourceFilesExist() {
    assertTrue(pathElement.contains(JAVA_CLASS_NAME1));
  }
  
  public void testNonExistingSourceFilesNotExist() {
    assertFalse(pathElement.contains(JAVA_CLASS_NAME1 + "xyz")); //$NON-NLS-1$
    assertFalse(pathElement.contains("123" + JAVA_CLASS_NAME1)); //$NON-NLS-1$
    assertFalse(pathElement.contains("")); //$NON-NLS-1$
  }

  public void testLoadNonExistingSourceFiles() throws IOException {
    assertNull(pathElement.load(JAVA_CLASS_NAME1 + "xyz")); //$NON-NLS-1$
    assertNull(pathElement.load("123" + JAVA_CLASS_NAME1)); //$NON-NLS-1$
    assertNull(pathElement.load("")); //$NON-NLS-1$
  }

  public void testLoadExistingSourceFiles() throws IOException {
    assertNotNull(pathElement.load(JAVA_CLASS_NAME1));
  }
}