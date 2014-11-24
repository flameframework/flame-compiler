package com.github.mvollebregt.flame.compiler

import java.io.{StringWriter, PrintWriter}

import org.junit.runner.RunWith
import org.mockito.Mockito._
import org.scalatest.junit.JUnitRunner
import org.scalatest.mock.MockitoSugar
import org.scalatest.FlatSpec

/**
 * Created by michel on 22-11-14.
 */
@RunWith(classOf[JUnitRunner])
class FreemarkerWriterWrapperTest extends FlatSpec with MockitoSugar {

  trait Fixture {
    val templateWriter = mock[TemplateWriter]
    val defaultOutput = new StringWriter()
    when(templateWriter.getWriterFor("default file")).thenReturn(defaultOutput)
    val processor = new PrintWriter(new FreemarkerWriterWrapper("default file", templateWriter))
  }

  "A template without file output markers" should "write a copy of the template to the default file" in new Fixture {
    // when
    processor.print("a template without file output markers")
    processor.close
    // then
    assert(defaultOutput.toString == "a template without file output markers")
  }


  "A template with a file output marker with single quote" should "write the inner contents to the specified output" in new Fixture {
    // given
    val specifiedOutput = new StringWriter()
    when(templateWriter.getWriterFor("specified name")).thenReturn(specifiedOutput)
    // when
    processor.println("<&output file='specified name'>inner contents</&output>")
    processor.close
    // then
    assert(specifiedOutput.toString == "inner contents")
  }


  "A template with a file output marker with double quote" should "write the inner contents to the specified output" in new Fixture {
    // given
    val specifiedOutput = new StringWriter()
    when(templateWriter.getWriterFor("another specified name")).thenReturn(specifiedOutput)
    // when
    processor.println("<&output file=\"another specified name\">other inner contents</&output>")
    processor.close
    // then
    assert(specifiedOutput.toString == "other inner contents")
  }

}
