import ply.lex as lex
import ply.yacc as yacc

########## lexical part ##########

tokens = ['NUM', 'PLUS', 'MULTI', 'EQULS', 'VNAME', 'LPAR', 'RPAR', 'SUB', 'DIV',
          'BREAK',
          'GT', 'LT', 'EQ', 'NEQ', 'GTEQ', 'LTEQ',
          'IF', 'THEN', 'ELSE', 'END', 'WHILE'
          ]

t_IF = r'IF'
t_THEN = r'THEN'
t_ELSE = r'ELSE'
t_END = r'END'
t_WHILE = r'WHILE'

t_BREAK = r'\;'

t_GT = r'\>'
t_LT = r'\<'
t_EQ = r'\~='
t_NEQ = r'\!='
t_GTEQ = r'\>='
t_LTEQ = r'\<='

t_PLUS = r'\+'
t_SUB = r'\-'
t_DIV = r'\/'
t_MULTI = r'\*'
t_EQULS = r'\='
t_LPAR = r'\('
t_RPAR = r'\)'
t_ignore = r' '

def t_NUM(t):
    r'\d+(\.\d*)?|True|False'
    if t.value == 'True' or t.value == 'False' :
        t.value = bool(t.value)
    else:
        t.value = float(t.value)
    return t

def t_VNAME(t):
    r'[V|v][a-zA-Z_0-9]*'
    t.type = 'VNAME'
    return t

def t_error(t):
    print('ellegal character')
    t.lexer.skip(1)
    
lexer = lex.lex()

########## syntax part ##########

## define priority

precedence = (
    ('left', 'SUB', 'PLUS'),
    ('left', 'MULTI', 'DIV')
)

def p_calc_v4_bool_if_loop(p):
    '''
    calc_v4_bool_if_loop : expression_in
        | ifthen
        | ifthenelse
        | whileloop
        | empty
    '''
    print(run(p[1]))



def p_ifthen(p):
    '''
    ifthen : IF expression_in THEN expression_in END
    '''
    if run(p[2]):
        p[0] = run(p[4])
def p_ifthenelse(p):
    '''
    ifthenelse : IF expression_in THEN expression_in ELSE expression_in END
    '''
    if run(p[2]):
        p[0] = run(p[4])
    else:
        p[0] = run(p[6])
def p_whileloop(p):
    '''
    whileloop : WHILE expression_in THEN expression_in END
    '''
    while run(p[2]):
        p[0] = run(p[4])

def p_expression_in(p):
    '''
    expression_in : LPAR expression_in RPAR
        | expression_in MULTI expression_in
        | expression_in DIV expression_in
        | expression_in PLUS expression_in
        | expression_in SUB expression_in
        | expression_in GT expression_in
        | expression_in LT expression_in
        | expression_in EQ expression_in
        | expression_in NEQ expression_in
        | expression_in GTEQ expression_in
        | expression_in LTEQ expression_in
        | VNAME EQULS expression_in
    '''
    if p[1] == '(' and p[3] == ')':
        p[0] = p[2]
    elif p[2] == '=':
        p[0] = ('=', p[1], p[3])
    else:
        p[0] = (p[2], p[1], p[3])
def p_expression_in_num(p):
    '''
    expression_in : NUM
    '''
    p[0] = p[1]
def p_expression_in_var(p):
    '''
    expression_in : VNAME
    '''
    p[0] = ('var', p[1])

    

def p_error(p):
    print('syntax error found')

def p_empty(p):
    '''
    empty :
    '''
    p[0] = None

## generating output from parser

parser = yacc.yacc()

env = {}
def run(p):
    global env
    
    if type(p) == tuple:
        if p[0] == '+':
            return run(p[1]) + run(p[2])
        elif p[0] == '-':
            return run(p[1]) - run(p[2])
        elif p[0] == '*':
            return run(p[1]) * run(p[2])
        elif p[0] == '/':
            return run(p[1]) / run(p[2])
        elif p[0] == '>':
            return run(p[1]) > run(p[2])
        elif p[0] == '<':
            return run(p[1]) < run(p[2])
        elif p[0] == '<=':
            return run(p[1]) <= run(p[2])
        elif p[0] == '>=':
            return run(p[1]) >= run(p[2])
        elif p[0] == '~=':
            return run(p[1]) == run(p[2])
        elif p[0] == '!=':
            return run(p[1]) != run(p[2])
        elif p[0] == '=':
            env[p[1]] = run(p[2])
            print(env)
        elif p[0] == 'var':
            if p[1] not in env:
                return 'Undeclare variable'
            else:
                return env[p[1]]
    else:
        return p

while True:
    try:
        s = input('>>input>> ')
    except EOFError:
        break
    parser.parse(s)
