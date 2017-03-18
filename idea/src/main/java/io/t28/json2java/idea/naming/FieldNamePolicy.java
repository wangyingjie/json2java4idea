package io.t28.json2java.idea.naming;

import com.google.common.base.CaseFormat;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.intellij.psi.codeStyle.VariableKind;
import com.squareup.javapoet.TypeName;
import io.t28.json2java.core.naming.DefaultNamePolicy;
import io.t28.json2java.core.naming.NamePolicy;

import javax.annotation.Nonnull;

public class FieldNamePolicy implements NamePolicy {
    private final JavaCodeStyleManager codeStyleManager;

    @Inject
    public FieldNamePolicy(@Nonnull JavaCodeStyleManager codeStyleManager) {
        this.codeStyleManager = codeStyleManager;
    }

    @Nonnull
    @Override
    public String convert(@Nonnull String name, @Nonnull TypeName type) {
        final String propertyName = DefaultNamePolicy.format(name, CaseFormat.LOWER_CAMEL);
        final String fieldName = codeStyleManager.propertyNameToVariableName(propertyName, VariableKind.FIELD);
        if (Strings.isNullOrEmpty(fieldName)) {
            throw new IllegalArgumentException("Cannot convert '" + name + "' to a field name");
        }
        return fieldName;
    }

}