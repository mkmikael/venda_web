import groovy.xml.MarkupBuilder

def camelCase2 = {
    return it.substring(0, 1).toLowerCase() + it.substring(1)
}
def plural = {
    def ultimo = it.length() - 1
    if (it.charAt(ultimo) == 'l')
        it.substring(0, ultimo) + 'is'
    else if (it.charAt(ultimo) == 'r')
        it + 'es'
    else if (it.substring(ultimo - 2) == 'cao')
        it.substring(0, ultimo - 2) + 'coes'
    else
        it + 's'
}

def xmlHeader = '<?xml version="1.0" encoding="UTF-8"?>\n'
def xhtmlHeader = '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">\n'
def name = 'Papel' 

def EL = '#{' + camelCase2(name) + 'Bean.' + camelCase2(name) + '.%s}'
def camelCase = camelCase2(name)
def pasta = camelCase2(name)
def lista = plural(camelCase2(name))
def propriedades = [nome:String]
//p.metaClass.properties.each { if (it.name != 'class') propriedades.put(it.name, it.type) }

def form = new StringWriter()
new MarkupBuilder(form).'ui:composition'(xmlns: 'http://www.w3.org/1999/xhtml',
    'xmlns:f': 'http://java.sun.com/jsf/core',
    'xmlns:h': 'http://java.sun.com/jsf/html',
    'xmlns:p': 'http://primefaces.org/ui',
    'xmlns:ui': 'http://java.sun.com/jsf/facelets',
    template: '/layout/main.xhtml') {
        'ui:define'(name: 'content') {
            'p:panel'(header: 'Criar ' + name) {
            'p:growl'(showDetail: true)
            'h:form' {
                'h:inputHidden'(id: 'id', value: String.format(EL, 'id'))
                'h:panelGrid'(columns: 2) {
                    propriedades.each {
                        'p:outputLabel'('for': it.key, value: it.key)
                        if (it.value == Date)
                            'p:calendar'(id: it.key, value: String.format(EL, it.key), pattern: 'dd/MM/yyyy', showOn: 'button')
                        else if (it.value.getSuperclass() == Number) {
                            'p:spinner'(id: it.key, value: String.format(EL, it.key))
                        } else if (it.value == Boolean) {
                            'p:selectBooleanCheckbox'(id: it.key, value: String.format(EL, it.key))
                        } else {
                            'p:inputText'(id: it.key, value: String.format(EL, it.key))
                        }
                    }
                }
                p {
                    'p:button'(value: 'Voltar', outcome: '/' + pasta + '/list.html')
                    'p:commandButton'(ajax: false, value: 'Salvar', action: '#{' + camelCase + 'Bean.salvar}')
                }
            }
        }
    }
}

def list = new StringWriter()
new MarkupBuilder(list).'ui:composition'(xmlns: 'http://www.w3.org/1999/xhtml',
    'xmlns:f': 'http://java.sun.com/jsf/core',
    'xmlns:h': 'http://java.sun.com/jsf/html',
    'xmlns:p': 'http://primefaces.org/ui',
    'xmlns:ui': 'http://java.sun.com/jsf/facelets',
    template: '/layout/main.xhtml') {
    'ui:define'(name: 'content') {
        'p:panel'(header: 'Listagem de ' + name) {
            'p:growl'(showDetail: true)
            p {
                'p:button'(value: 'Criar ' + name, outcome: '/' + pasta + '/form.xhtml')
            }
            'h:form' {
                'p:dataTable'(id: name.toLowerCase() + 'List',  value: String.format('#{%s.%s}', camelCase + 'Bean', lista), var: name.toLowerCase()
                , emptyMessage: 'Sem registros.') {
                    propriedades.each {
                        def attr = it.key
                        'p:column'(headerText: attr) {
                            'h:outputText'(value: String.format('#{%s.%s}', camelCase, attr))
                        }
                    }
                    'p:column'(width: 85) {
                        'p:commandButton'(ajax: false, icon: 'ui-icon-pencil', action: '/' + pasta + '/form.xhtml') {
                            'f:setPropertyActionListener'(value: '#{' + camelCase + '}', target: '#{' + camelCase + 'Bean.' + camelCase + '}')
                        }
                    }
                    'p:column'(width: 85) {
                        'p:commandButton'(ajax: false, icon: 'ui-icon-trash', action: '#{' + camelCase + 'Bean.deletar}') {
                            'f:setPropertyActionListener'(value: '#{' + camelCase + '}', target: '#{' + camelCase + 'Bean.' + camelCase + '}')
                        }
                    }
                }
            }
        }
    }
}
println xmlHeader + list.toString().replace('\'', '"')
//println xmlHeader + list.toString().replace('\'', '"')
println ' '