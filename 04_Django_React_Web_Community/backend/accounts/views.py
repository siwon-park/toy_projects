from django.shortcuts import redirect
from rest_framework import response
from django.http import JsonResponse
from rest_framework.views import APIView
from accounts.serializers import SignUpSerializer
from rest_framework_simplejwt.tokens import RefreshToken

# Create your views here.
class SignUpView(APIView):
    serializer_class = SignUpSerializer

    def post(self, request):
        serializer = self.serializer_class(data=request.data)
        if serializer.is_valid(raise_exception=True):
            user = serializer.save(request)
            print(user, "+++++++++++++++++++++++++++++++++++++++++++++++")
            token = RefreshToken.for_user(user)
            refresh = str(token)
            access = str(token.access_token)
            return JsonResponse({'user': {'id': user.id, 'email': user.email}, 'access': access, 'refresh': refresh})


class LoginView(APIView):

    def post(self, request):
        pass


